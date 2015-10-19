package com.newsup.lister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.newsup.R;
import com.newsup.kernel.Date;
import com.newsup.kernel.News;
import com.newsup.kernel.list.NewsList;
import com.newsup.kernel.list.NewsMap;

import java.util.Collection;
import java.util.Comparator;

public class NewsLister extends ArrayAdapter<News> implements Comparator<News> {

    private NewsMap newsmap;
    private NewsList newslist;
    private LayoutInflater inflater;

    public NewsLister(Context context) {
        super(context, -1, new NewsList());
        newsmap = new NewsMap(this);
        newslist = new NewsList(newsmap);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.i_news, parent, false);
        }
        News news = newslist.get(position);

        ((TextView) view.findViewById(R.id.title)).setText(news.title);
        ((TextView) view.findViewById(R.id.date)).setText("Hace " + news.date.getAge());
        view.findViewById(R.id.logo).setBackgroundDrawable(news.site.icon);

        String description = news.description;
        if (description.length() > 100) {
            int index = description.indexOf(" ", 99);
            if (index != -1) description = description.substring(0, index) + "...";
        }
        ((TextView) view.findViewById(R.id.description)).setText(description);
        return view;
    }

    @Override
    public void clear() {
        newsmap.clear();
        newslist.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return newslist.size();
    }

    @Override
    public News getItem(int position) {
        return newslist.get(position);
    }

    @Override
    public void add(News news) {
        newsmap.add(news);
        newslist = new NewsList(newsmap);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(Collection<? extends News> list) {
        newsmap.addAll(list);
        newslist = new NewsList(newsmap);
        notifyDataSetChanged();
    }

    private void debug(String text) {
        android.util.Log.d(this.getClass().getSimpleName(), text);
    }


    @Override
    public int compare(News n1, News n2) {
        int comparison = -Date.compare(n1.date, n2.date);
        return comparison != 0 ? comparison : (n1.id < n2.id ? -1 : (n1.id == n2.id ? 0 : 1));
    }
}
