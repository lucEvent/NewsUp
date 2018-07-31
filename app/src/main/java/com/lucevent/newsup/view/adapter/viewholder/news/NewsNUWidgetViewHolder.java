package com.lucevent.newsup.view.adapter.viewholder.news;

import android.graphics.Color;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class NewsNUWidgetViewHolder extends NewsWebViewViewHolder {

	public NewsNUWidgetViewHolder(View v)
	{
		super(v);
		WebView webView = ((WebView) v);
		webView.setBackgroundColor(Color.TRANSPARENT);

		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
		webSettings.setBuiltInZoomControls(false);
		webSettings.setDisplayZoomControls(false);
	}

	@Override
	public void bind(boolean darkStyle)
	{
		WebView webView = ((WebView) itemView);
		webView.loadData("<style>body{margin:20px}</style>" + elem.getContent(), "text/html", "utf-8");
		webView.onResume();
	}

}
