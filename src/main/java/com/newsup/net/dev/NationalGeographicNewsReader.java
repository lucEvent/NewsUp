package com.newsup.net.dev;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class NationalGeographicNewsReader extends NewsReader {

    public NationalGeographicNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));
        SECTIONS.add(new Section("", 0, ""));

    }

    @Override
    public News readNewsContent(News news) {
        return news;
    }

}
