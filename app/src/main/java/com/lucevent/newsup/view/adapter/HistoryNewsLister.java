package com.lucevent.newsup.view.adapter;

import android.view.View;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.kernel.util.HistoryNews;

import java.util.Comparator;

public class HistoryNewsLister extends NewsAdapter implements Comparator<News> {

    public HistoryNewsLister(NewsArray dataset, View.OnClickListener itemListener)
    {
        super(dataset, itemListener);
    }

    /*
        public HistoryNewsLister(Context context) {
            super(context);
            newsmap = new NewsMap(this);
        }
    */
    @Override
    public int compare(News lhs, News rhs)
    {
        HistoryNews n1 = (HistoryNews) lhs;
        HistoryNews n2 = (HistoryNews) rhs;
        return n2.hid < n1.hid ? -1 : (n2.hid == n1.hid ? 0 : 1);
    }

}
