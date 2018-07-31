package com.lucevent.newsup.view.adapter.viewholder.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.parse.NewsParagraph;

public class NewsNIMediaViewHolder extends NewsBlockquoteViewHolder {

	public NewsNIMediaViewHolder(View v, LayoutInflater inflater)
	{
		super(v, 1);
		NewsParagraph np = new NewsParagraph();
		np.setContent(v.getContext().getString(R.string.no_internet_no_media));

		NewsTextViewHolder messageViewHolder = new NewsTextViewHolder(inflater.inflate(R.layout.i_news_paragraph, (ViewGroup) itemView, false), NewsTextViewHolder.TEXT_SIZE_NORMAL);
		messageViewHolder.setNewsElement(np);

		addElement(messageViewHolder);
	}

}
