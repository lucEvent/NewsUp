package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.io.LogoManager;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private View logo;
    private TextView title, description, date;
    private ImageButton bookmarkButton;
    private ImageView picture;

    public NewsViewHolder(View v, View.OnClickListener onBookmark)
    {
        super(v);

        title = (TextView) v.findViewById(R.id.title);
        description = (TextView) v.findViewById(R.id.description);
        date = (TextView) v.findViewById(R.id.date);
        logo = v.findViewById(R.id.logo);
        bookmarkButton = (ImageButton) v.findViewById(R.id.button_bookmark);
        bookmarkButton.setOnClickListener(onBookmark);
        picture = (ImageView) v.findViewById(R.id.picture);
    }

    public void bind(News news, boolean showSiteLogo, boolean loadImage, boolean bookmarked)
    {
        if (showSiteLogo) {
            logo.setVisibility(View.VISIBLE);
            logo.setBackground(LogoManager.getLogo(news.site_code, LogoManager.Size.I_ITEM));
        } else
            logo.setVisibility(View.GONE);

        if (loadImage && news.enclosures != null && !news.enclosures.isEmpty() && !news.enclosures.get(0).src.isEmpty()) {

            picture.setImageDrawable(null);

            Glide.with(picture.getContext())
                    .load(news.enclosures.get(0).src)
                    .into(picture);
        } else {
            picture.setImageDrawable(null);
        }

        title.setText(Html.fromHtml(news.title));
        description.setText(news.description);
        date.setText(Date.getAge(news.date));
        //  bookmarkButton.setImageResource(bookmarked ? R.drawable.ic_bookmark : R.drawable.ic_bookmark_border);
        bookmarkButton.setSelected(bookmarked);

        bookmarkButton.setTag(news);
        itemView.setTag(news);
    }

}
