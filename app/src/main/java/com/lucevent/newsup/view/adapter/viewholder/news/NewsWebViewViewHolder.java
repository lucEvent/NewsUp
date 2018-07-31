package com.lucevent.newsup.view.adapter.viewholder.news;

import android.view.View;
import android.webkit.WebView;

public abstract class NewsWebViewViewHolder extends NewsElementViewHolder {

	public static final int[] FONT_SIZE_VALUES = new int[]{75, 87, 100, 112, 125};

	NewsWebViewViewHolder(View v)
	{
		super(v);
	}

	@Override
	public void unbind()
	{
		WebView webView = ((WebView) itemView);
		webView.loadData("", "text/html", "utf-8");
		webView.onPause();
	}

	@Override
	public void setTextSize(int font_size)
	{
		((WebView) itemView).getSettings().setTextZoom(FONT_SIZE_VALUES[font_size]);
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
