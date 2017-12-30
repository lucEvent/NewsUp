package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.event.Event;

public class EventViewHolder extends RecyclerView.ViewHolder {

    private TextView title, topic;
    private ImageView picture;

    public EventViewHolder(View v)
    {
        super(v);

        title = (TextView) v.findViewById(R.id.title);
        topic = (TextView) v.findViewById(R.id.topic);
        picture = (ImageView) v.findViewById(R.id.picture);
    }

    public void bind(Event event)
    {
        picture.setImageBitmap(null);

        Glide.with(picture.getContext())
                .load(event.imgSrc)
                .into(picture);

        picture.setVisibility(View.VISIBLE);

        title.setText(event.title);
        topic.setText(event.topic);

        itemView.setTag(event);
    }

}
