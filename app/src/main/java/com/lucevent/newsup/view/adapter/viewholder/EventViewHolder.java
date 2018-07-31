package com.lucevent.newsup.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.event.Event;

public class EventViewHolder extends RecyclerView.ViewHolder {

	private TextView mTitle, mTopic;
	private ImageView mPicture;

	public EventViewHolder(View v)
	{
		super(v);

		mTitle = (TextView) v.findViewById(R.id.title);
		mTopic = (TextView) v.findViewById(R.id.topic);
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
		mTopic.setText(event.topic);

		itemView.setTag(event);
	}

}
