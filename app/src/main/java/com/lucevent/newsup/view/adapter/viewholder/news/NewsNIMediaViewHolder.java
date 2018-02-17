package com.lucevent.newsup.view.adapter.viewholder.news;

import android.view.View;

import com.lucevent.newsup.R;

public class NewsNIMediaViewHolder extends NewsBlockquoteViewHolder {

    public NewsNIMediaViewHolder(View v)
    {
        super(v);
        content.setText(R.string.no_internet_no_media);
    }

    @Override
    public void bind(boolean darkStyle)
    {
    }

}
