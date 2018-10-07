package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.event.Event;

public class EventViewHolder extends RecyclerView.ViewHolder {

	private TextView mTitle;
	private ImageView mPicture;

	public EventViewHolder(View v)
	{
		super(v);

		mTitle = (TextView) v.findViewById(R.id.title);
		mPicture = (ImageView) v.findViewById(R.id.picture);
	}

	public void bind(Event event)
	{
		mPicture.setImageBitmap(null);

		Glide.with(mPicture.getContext())
				.load(event.imgSrc)
				.into(mPicture);

		mPicture.setVisibility(View.VISIBLE);

		mTitle.setText(event.title);

		itemView.setTag(event);
	}

}
