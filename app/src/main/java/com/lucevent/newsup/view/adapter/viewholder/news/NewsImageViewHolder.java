package com.lucevent.newsup.view.adapter.viewholder.news;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lucevent.newsup.parse.NewsImage;

public class NewsImageViewHolder extends NewsElementViewHolder {

    private static RequestOptions glideOptions = new RequestOptions().fitCenter();

    public NewsImageViewHolder(View v)
    {
        super(v);
    }

    @Override
    public void bind()
    {
        Glide.with(itemView.getContext())
                .load(((NewsImage) elem).getContent())
                .apply(glideOptions)
                .into((ImageView) itemView);
    }

    @Override
    public void setTextSize(int font_size)
    {
    }

    @Override
    public void setStyle(boolean darkStyle)
    {
    }

    @Override
    public void setLinkColor(int linkColor)
    {
    }

}