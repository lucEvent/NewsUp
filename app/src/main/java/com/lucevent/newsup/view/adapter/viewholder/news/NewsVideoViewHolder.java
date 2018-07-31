package com.lucevent.newsup.view.adapter.viewholder.news;

import android.graphics.Color;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.lucevent.newsup.parse.NewsVideo;

public class NewsVideoViewHolder extends NewsWebViewViewHolder {

	public NewsVideoViewHolder(View v)
	{
		super(v);
		WebView webView = ((WebView) v);
		webView.setBackgroundColor(Color.TRANSPARENT);

		WebSettings webSettings = webView.getSettings();
		webSettings.setBuiltInZoomControls(false);
		webSettings.setDisplayZoomControls(false);
	}

	@Override
	public void bind(boolean darkStyle)
	{
		String html = "<style>body{margin:0}video{width:100%;min-height:300px;}</style>" + ((NewsVideo) elem).getContent();

		WebView webView = ((WebView) itemView);
		webView.loadData(html, "text/html", "utf-8");
		webView.onResume();
	}

}
