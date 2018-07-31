package com.lucevent.newsup.view.adapter.viewholder.news;

import android.graphics.Color;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.lucevent.newsup.parse.NewsIframe;

public class NewsIframeViewHolder extends NewsWebViewViewHolder {

	public NewsIframeViewHolder(View v)
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
		String html = "<style>body{margin:0}</style><iframe style='width:100%;min-height:300px;' frameborder='0' allowfullscreen src='" + ((NewsIframe) elem).getContent() + "' scrolling='no'></iframe>";
		WebView webView = ((WebView) itemView);
		webView.loadData(html, "text/html", "utf-8");
		webView.onResume();
	}

}
