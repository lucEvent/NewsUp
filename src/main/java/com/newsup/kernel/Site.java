package com.newsup.kernel;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.newsup.ia.IASite;
import com.newsup.kernel.list.NewsList;
import com.newsup.kernel.list.NewsMap;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;
import com.newsup.settings.SiteSettings;

import java.io.InputStream;

public class Site extends IASite {

    public static final int THEME_APP = 0xFF86c700;
    public static final int THEME_SPAIN = 0xFFEE3333;
    public static final int THEME_CATALONIA = 0xFFfff800;
    public static final int THEME_SWEDEN = 0xFF006ca6;
    public static final int THEME_FINLAND = 0xFF003580;
    public static final int THEME_INTERNATIONAL = 0xFFDDDDDD;
    public static final int THEME_TECHNOLOGY = 0xFF90c3d4;
    public static final int THEME_BLOGS = 0xFF66AA55;
    public static final int THEME_MAGAZINES = 0xFF444444;

    /**
     * Variables
     **/
    public final String name;
    public final ColorDrawable color;
    public final ColorDrawable theme;
    public final Drawable icon;

    public NewsList news;
    public NewsMap historial;

    public SiteSettings settings;

    private NewsReader reader;

    public Site(int code, String name, int color, int theme, InputStream icon, NewsReader reader) {
        super(code);
        this.name = name;
        this.color = new ColorDrawable(color);
        this.theme = new ColorDrawable(theme);
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
