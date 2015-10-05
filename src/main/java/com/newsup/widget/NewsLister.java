package com.newsup.widget;

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

public class NewsLister extends ArrayAdapter<News> {

    private LayoutInflater inflater;

    public NewsLister(Context context, NewsList values) {
        super(context, -1, values);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        News news = getItem(position);

        if (news.link == null) {
            view = inflater.inflate(R.layout.i_group_header_list, parent, false);
            ((TextView) view.findViewById(R.id.section_name)).setText(news.title);
        } else {
            view = inflater.inflate(R.layout.i_news, parent, false);
            ((TextView) view.findViewById(R.id.title)).setText(news.title);
            ((TextView) view.findViewById(R.id.date)).setText(news.date.toString());
            ((TextView) view.findViewById(R.id.description)).setText(news.description);
        }
        return view;
    }

}
