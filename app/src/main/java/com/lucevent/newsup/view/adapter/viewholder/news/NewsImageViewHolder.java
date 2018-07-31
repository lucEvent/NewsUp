package com.lucevent.newsup.view.adapter.viewholder.news;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.parse.NewsImage;

public class NewsImageViewHolder extends NewsElementViewHolder {

	private static RequestOptions glideOptions = new RequestOptions().fitCenter();

	public NewsImageViewHolder(View v, View.OnLongClickListener longClickListener)
	{
		super(v);
		v.setOnLongClickListener(longClickListener);
	}

	@Override
	public void bind(boolean darkStyle)
	{
		String imgSrc = ((NewsImage) elem).getContent();
		itemView.setTag(AppCode.TAG_IMAGE, imgSrc);

		Glide.with(itemView.getContext())
				.load(imgSrc)
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