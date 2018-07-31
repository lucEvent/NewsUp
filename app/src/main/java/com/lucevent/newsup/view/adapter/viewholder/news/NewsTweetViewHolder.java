package com.lucevent.newsup.view.adapter.viewholder.news;

import android.graphics.Color;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.lucevent.newsup.parse.NewsTweet;

public class NewsTweetViewHolder extends NewsWebViewViewHolder {

	private String script;

	public NewsTweetViewHolder(View v, String script)
	{
		super(v);
		this.script = "<style>blockquote{margin:16px;padding:16px;}</style>" + script;

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
		String style = "<style>blockquote{background-color:" + (darkStyle ? "#333" : "#eee") + ";color:" + (darkStyle ? "#eee" : "#111") + ";}</style>";

		WebView webView = ((WebView) itemView);
		webView.loadDataWithBaseURL("https://twitter.com", script + style + ((NewsTweet) elem).getContent(), "text/html", "utf-8", "");
		webView.onResume();
	}

	@Override
	public void setStyle(boolean darkStyle)
	{
		String bgColor = darkStyle ? "#333" : "#eee";
		String textColor = darkStyle ? "#eee" : "#111";
		WebView webView = ((WebView) itemView);
		webView.evaluateJavascript(
				"e=document.getElementsByTagName('blockquote');" +
						"for(var i=0;i<e.length;i++){" +
						"e[i].style.backgroundColor='" + bgColor + "';" +
						"e[i].style.color='" + textColor + "'" +
						"}", null);
	}

}
