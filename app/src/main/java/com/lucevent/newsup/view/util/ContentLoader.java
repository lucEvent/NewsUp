package com.lucevent.newsup.view.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class ContentLoader extends RecyclerView.OnScrollListener {

    private static final int THRESHOLD = 5;

    private LinearLayoutManager mLinearLayoutManager;

    public ContentLoader(LinearLayoutManager linearLayoutManager)
    {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy)
    {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = mLinearLayoutManager.getItemCount();
        int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();

        if (totalItemCount > 0 &&
                (totalItemCount - visibleItemCount) <= (lastVisibleItem + THRESHOLD))
            onLoadMore();
    }

    public abstract void onLoadMore();

}