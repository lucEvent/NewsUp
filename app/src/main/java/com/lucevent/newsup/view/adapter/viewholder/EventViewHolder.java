package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.event.Event;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;

public class EventViewHolder extends RecyclerView.ViewHolder {

    private View container;
    private TextView title, description;
    private ImageView picture;

    public EventViewHolder(View v)
    {
        super(v);

        title = (TextView) v.findViewById(R.id.title);
        description = (TextView) v.findViewById(R.id.description);
        picture = (ImageView) v.findViewById(R.id.picture);

        container = v;
    }

    public void bind(Event event)
    {
        picture.setImageBitmap(null);

        new Picasso.Builder(picture.getContext())
                .downloader(new OkHttp3Downloader(new OkHttpClient()))
                .build()
                .load(event.imgSrc)
                .fit()
                .into(picture);

        picture.setVisibility(View.VISIBLE);

        title.setText(Html.fromHtml(event.title));
        description.setText(Html.fromHtml(event.description));

        container.setTag(event);
    }

}
