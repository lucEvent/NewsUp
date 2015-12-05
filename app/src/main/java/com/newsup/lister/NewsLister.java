package com.newsup.lister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.newsup.R;
import com.newsup.kernel.NewsDataCenter;
import com.newsup.kernel.basic.News;
import com.newsup.kernel.set.NewsMap;
import com.newsup.kernel.util.Date;

import java.util.ArrayList;
import java.util.Collection;

public class NewsLister extends ArrayAdapter<News> {

    protected NewsMap newsmap;
    protected ArrayList<News> newslist;

    protected boolean update;
    protected boolean showSiteLogo;

    protected LayoutInflater inflater;

    public NewsLister(Context context) {
        super(context, R.layout.i_news, new ArrayList<News>());
        newsmap = new NewsMap();
        newslist = new ArrayList<News>(newsmap);
        update = false;
        showSiteLogo = true;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.i_news, parent, false);
        }
        News news = getItem(position);

        ((TextView) view.findViewById(R.id.title)).setText(news.title);
        ((TextView) view.findViewById(R.id.date)).setText(Date.getAge(news.date));

        View logo = view.findViewById(R.id.logo);
        if (showSiteLogo) {
            logo.setVisibility(View.VISIBLE);
            logo.setBackground(NewsDataCenter.getSiteByCode(news.site_code).icon);
        } else {
            logo.setVisibility(View.GONE);
        }

        String description = news.description;
        if (description.length() > 100) {
            int index = description.indexOf(" ", 99);
            if (index != -1) description = description.substring(0, index) + "...";
        }
        ((TextView) view.findViewById(R.id.description)).setText(description);
        return view;
    }

    public void showSiteLogo(boolean showSiteLogo) {
        this.showSiteLogo = showSiteLogo;
    }

    @Override
    public void clear() {
        newsmap.clear();
        newslist.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return newsmap.size();
    }

    @Override
    public News getItem(int position) {
        if (update) {
            newslist.clear();
            newslist.addAll(newsmap);
            update = false;
        }
        return newslist.get(position);
    }

    @Override
    public void add(News news) {
        newsmap.add(news);
        update = true;
        notifyDataSetChanged();
    }

    @Override
    public void addAll(Collection<? extends News> list) {
        newsmap.addAll(list);
        update = true;
        notifyDataSetChanged();
    }

    private void debug(String text) {
        android.util.Log.d(this.getClass().getSimpleName(), text);
    }

}
