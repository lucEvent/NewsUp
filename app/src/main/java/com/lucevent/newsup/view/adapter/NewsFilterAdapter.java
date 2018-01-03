package com.lucevent.newsup.view.adapter;

import android.support.v7.util.SortedList;
import android.view.View;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.view.util.NewsAdapterList;

import java.util.ArrayList;
import java.util.TreeSet;

public class NewsFilterAdapter extends NewsAdapter {

    private ArrayList<News> originalValues, filterValues, lastQueryValues;
    private String lastFilter = "";

    public NewsFilterAdapter(View.OnClickListener onClick, View.OnLongClickListener onLongClick,
                             View.OnClickListener onBookmarkClick, NewsAdapterList.SortBy sortBy)
    {
        super(onClick, onLongClick, onBookmarkClick, sortBy);
    }

    public ArrayList<News> getDataSet()
    {
        init();
        return originalValues;
    }

    private void init()
    {
        if (originalValues == null) {
            SortedList<News> sortedList = dataSet;
            originalValues = new ArrayList<>(sortedList.size());
            lastQueryValues = new ArrayList<>(sortedList.size());
            for (int i = 0; i < sortedList.size(); i++)
                originalValues.add(sortedList.get(i));
        }
    }

    public void filter(String filter)
    {
        init();

        ArrayList<News> searchableValues;

        if (filterValues != null)
            searchableValues = filterValues;
        else
            searchableValues = filter.startsWith(lastFilter) && !lastFilter.isEmpty() ? lastQueryValues : originalValues;

        lastFilter = filter;

        ArrayList<News> queryValues = new ArrayList<>();
        for (News n : searchableValues)
            if (n.title.toLowerCase().contains(filter))
                queryValues.add(n);

        replaceAll(queryValues);

        lastQueryValues = queryValues;
    }

    public void filter(TreeSet<Integer> filter)
    {
        init();

        ArrayList<News> queryValues = new ArrayList<>();
        for (News n : originalValues)
            if (filter.contains(n.site_code))
                queryValues.add(n);

        filterValues = queryValues;

        if (!lastFilter.isEmpty())
            filter(lastFilter);
        else
            replaceAll(queryValues);
    }

    @Override
    public void clear()
    {
        super.clear();
        originalValues = null;
        lastQueryValues = null;
        lastFilter = "";
    }

}
