package com.lucevent.newsup.view.adapter.viewholder.news;

import android.graphics.Color;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.lucevent.newsup.parse.NewsInstagram;

public class NewsInstagramViewHolder extends NewsWebViewViewHolder {

    private String script;

    public NewsInstagramViewHolder(View v, String script)
    {
        super(v);
        this.script = script;

        WebView webView = ((WebView) v);
        webView.setBackgroundColor(Color.TRANSPARENT);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);
    }

    @Override
    public void bind(boolean darkStyle)
    {
        WebView webView = ((WebView) itemView);
        webView.loadData(((NewsInstagram) elem).getContent() + "<script async defer src='https://platform.instagram.com/en_US/embeds.js'></script>", "text/html", "utf-8");
        webView.onResume();
    }

}