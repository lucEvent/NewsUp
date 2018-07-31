package com.lucevent.newsup.view.adapter.viewholder.news;

import android.view.View;

import com.lucevent.newsup.parse.NewsElement;

public abstract class NewsElementViewHolder {


	static final int[] FONT_SIZE_TITLE_VALUES = new int[]{16, 18, 20, 22, 24};
	static final int[] FONT_SIZE_LARGE_VALUES = new int[]{14, 16, 18, 20, 22};
	static final int[] FONT_SIZE_NORMAL_VALUES = new int[]{12, 14, 16, 18, 20};
	static final int[] FONT_SIZE_SMALL_VALUES = new int[]{9, 11, 13, 15, 17};

	static final int LIGHT_TEXT_COLOR = (int) Long.parseLong("ff14171a", 16);
	static final int DARK_TEXT_COLOR = (int) Long.parseLong("ffffffff", 16);
	static final int LIGHT_BACKGROUND_COLOR = (int) Long.parseLong("ffffffff", 16);
	static final int LIGHT_BLOCKQUOTE_BACKGROUND_COLOR = (int) Long.parseLong("ffeeeeee", 16);
	static final int DARK_BACKGROUND_COLOR = (int) Long.parseLong("ff1b2836", 16);
	static final int DARK_BLOCKQUOTE_BACKGROUND_COLOR = (int) Long.parseLong("ff444444", 16);

	protected final View itemView;

	protected NewsElement elem;

	public NewsElementViewHolder(View v)
	{
		itemView = v;
	}

	public abstract void bind(boolean darkStyle);

	public void unbind()
	{
	}

	public abstract void setTextSize(int font_size);

	public abstract void setStyle(boolean darkStyle);

	public abstract void setLinkColor(int linkColor);

	public View get()
	{
		return itemView;
	}

	public void setNewsElement(NewsElement ne)
	{
		elem = ne;
	}

}
