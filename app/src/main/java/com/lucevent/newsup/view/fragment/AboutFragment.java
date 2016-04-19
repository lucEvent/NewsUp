package com.lucevent.newsup.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.lucevent.newsup.R;

public class AboutFragment extends android.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        WebView view = new WebView(getActivity());

        ViewGroup.LayoutParams layout_params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layout_params);

        view.setPersistentDrawingCache(WebView.PERSISTENT_NO_CACHE);

        WebSettings webSettings = view.getSettings();
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);

        view.loadData(getString(R.string.about_content), "text/html", "utf-8");

        return view;
    }
}
