package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.io.LogoManager;

public class NewsCompactViewHolder extends RecyclerView.ViewHolder {

    private static final RequestOptions OPTIONS = new RequestOptions().centerCrop();

    private View logo;
    private TextView title, date;
    private ImageButton bookmarkButton;
    private ImageView picture;

    public NewsCompactViewHolder(View v, View.OnClickListener onBookmark)
    {
        super(v);

        title = (TextView) v.findViewById(R.id.title);
        date = (TextView) v.findViewById(R.id.date);
        logo = v.findViewById(R.id.logo);
        bookmarkButton = (ImageButton) v.findViewById(R.id.button_bookmark);
        bookmarkButton.setOnClickListener(onBookmark);
        picture = (ImageView) v.findViewById(R.id.picture);
    }

    public void bind(News news, boolean showSiteLogo, boolean bookmarked)
    {
        if (showSiteLogo) {
            logo.setVisibility(View.VISIBLE);
            logo.setBackground(LogoManager.getLogo(news.site_code, LogoManager.Size.I_ITEM));
        } else
            logo.setVisibility(View.GONE);

        // img
        picture.setVisibility(View.VISIBLE);
        picture.setImageDrawable(null);

        Glide.with(picture.getContext())
                .applyDefaultRequestOptions(OPTIONS)
                .load(news.enclosures.get(0).src)
                .into(picture);
        // end img

        title.setText(Html.fromHtml(news.title));
        date.setText(Date.getAge(news.date));
        bookmarkButton.setSelected(bookmarked);

        bookmarkButton.setTag(news);
        itemView.setTag(news);
    }

}
