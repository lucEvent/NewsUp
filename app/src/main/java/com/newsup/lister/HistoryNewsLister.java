package com.newsup.lister;

import android.content.Context;

import com.newsup.kernel.basic.HistoryNews;
import com.newsup.kernel.basic.News;
import com.newsup.kernel.set.NewsMap;

import java.util.Comparator;

public class HistoryNewsLister extends NewsLister implements Comparator<News> {

    public HistoryNewsLister(Context context) {
        super(context);
        newsmap = new NewsMap(this);
    }

    @Override
    public int compare(News lhs, News rhs) {
        HistoryNews n1 = (HistoryNews) lhs;
        HistoryNews n2 = (HistoryNews) rhs;
        return n2.hid < n1.hid ? -1 : (n2.hid == n1.hid ? 0 : 1);
    }
}
