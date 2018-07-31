package com.lucevent.newsup.view.adapter.viewholder.news;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class NewsBlockquoteViewHolder extends NewsElementViewHolder {

	private ArrayList<NewsElementViewHolder> elems;

	public NewsBlockquoteViewHolder(View v, int capacity)
	{
		super(v);
		elems = new ArrayList<>(capacity);
	}

	public void addElement(NewsElementViewHolder viewHolder)
	{
		elems.add(viewHolder);
		((ViewGroup) itemView).addView(viewHolder.itemView);
	}

	@Override
	public void bind(boolean darkStyle)
	{
		for (NewsElementViewHolder e : elems)
			e.bind(darkStyle);
	}

	@Override
	public void setTextSize(int font_size)
	{
		for (NewsElementViewHolder e : elems)
			e.setTextSize(font_size);
	}

	@Override
	public void setStyle(boolean darkStyle)
	{
		for (NewsElementViewHolder e : elems)
			e.setStyle(darkStyle);
		itemView.setBackgroundColor(darkStyle ? DARK_BLOCKQUOTE_BACKGROUND_COLOR : LIGHT_BLOCKQUOTE_BACKGROUND_COLOR);
	}

	@Override
	public void setLinkColor(int linkColor)
	{
		for (NewsElementViewHolder e : elems)
			e.setLinkColor(linkColor);
	}

}
