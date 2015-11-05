package com.newsup.kernel;

import android.graphics.drawable.Drawable;

import com.newsup.R;
import com.newsup.ia.IASite;
import com.newsup.kernel.list.NewsList;
import com.newsup.kernel.list.NewsMap;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;
import com.newsup.settings.SiteSettings;

import java.io.InputStream;

public class Site extends IASite {

    public static final int MAIN_NAME = R.string.mycustomfeed;
    public static final int MAIN_COLOR = 0xFF86c700;

    /**
     * Variables
     **/
    public final String name;
    public final int color;
    public final Drawable icon;

    public NewsList news;
    public NewsMap historial;

    public SiteSettings settings;

    private NewsReader reader;

    public Site(int code, String name, int color, InputStream icon, NewsReader reader) {
        super(code);
        this.name = name;
        this.color = color;
        this.icon = Drawable.createFromStream(icon, null);
        this.reader = reader;
        this.historial = new NewsMap();
    }

    public NewsReader getReader() {
        return reader;
    }

    public SectionList getSections() {
        return reader.SECTIONS;
    }

}
