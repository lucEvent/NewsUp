package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class CNNNewsReader extends NewsReader {

    public CNNNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
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

    protected void debug(String text) {
        android.util.Log.d("##CNNNewsReader##", text);
    }
}


