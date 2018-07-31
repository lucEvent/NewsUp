package com.lucevent.newsup.view.adapter.viewholder.news;

import android.graphics.Color;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class NewsAudioViewHolder extends NewsWebViewViewHolder {

	public NewsAudioViewHolder(View v)
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
		String html = "<style>body{margin:0}audio{width:100%}</style>" + elem.getContent();

		WebView webView = ((WebView) itemView);
		webView.loadData(html, "text/html", "utf-8");
		webView.onResume();
	}

}