package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.Date;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.io.LogoManager;

public class NewsViewHolder extends RecyclerView.ViewHolder /*implements Target*/ {

    private View container, logo;
    private TextView title, description, date;
    private ImageButton bookmarkButton;

    //  private ImageView picture;
    // private News news;

    public NewsViewHolder(View v, View.OnClickListener onBookmark)
    {
        super(v);

        title = (TextView) v.findViewById(R.id.title);
        description = (TextView) v.findViewById(R.id.description);
        date = (TextView) v.findViewById(R.id.date);
        // picture = (ImageView) v.findViewById(R.id.picture);
        logo = v.findViewById(R.id.logo);
        bookmarkButton = (ImageButton) v.findViewById(R.id.button_bookmark);
        bookmarkButton.setOnClickListener(onBookmark);

        container = v;
    }

    public static void populateViewHolder(NewsViewHolder holder, News news, boolean showSiteLogo, boolean bookmarked)
    {
        /*     holder.picture.setImageBitmap(null);
             holder.news = news;
        if (!news.enclosures.isEmpty()) {
            // System.out.println("Src:"+news.enclosures.get(0).src);
            Picasso.with(holder.container.getContext())
                    .load(news.enclosures.get(0).src)
                    .fit()
                    .into(holder.picture);
        }*/

        if (showSiteLogo) {
            holder.logo.setVisibility(View.VISIBLE);
            holder.logo.setBackground(LogoManager.getLogo(news.site_code, LogoManager.Size.I_ITEM));
        } else
            holder.logo.setVisibility(View.GONE);

        holder.title.setText(news.title);
        holder.description.setText(news.description);
        holder.date.setText(Date.getAge(news.date));
        holder.bookmarkButton.setImageResource(bookmarked ? R.drawable.ic_bookmark : R.drawable.ic_bookmark_border);

        holder.bookmarkButton.setTag(news);
        holder.container.setTag(news);
    }

    /*
    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from)
    {
        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, container.getWidth(), container.getHeight());

        picture.setImageBitmap(scaledBitmap);
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable)
    {
        title.setTextColor(TEXT_COLOR_NO_IMAGE);
        date.setTextColor(TEXT_COLOR_NO_IMAGE);
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable)
    {
    }
*/
}
