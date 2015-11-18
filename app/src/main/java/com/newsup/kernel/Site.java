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
import java.util.ArrayList;

public class Site extends IASite {

    public static final int MAIN_NAME = R.string.mycustomfeed;
    public static final int MAIN_COLOR = 0xFF86c700;

    /**
     * Variables
     **/
    public final String name;
    public final int color;
    public final Drawable icon;

    private ArrayList<Section> sections;

    public SiteSettings settings;

    public NewsList news;
    public NewsMap historial;

    public Site(int code, String name, int color, InputStream icon, ArrayList<Section> sections) {
        super(code);
        this.name = name;
        this.color = color;
        this.icon = Drawable.createFromStream(icon, null);
        this.sections = sections;
        this.historial = new NewsMap();
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

}
