package com.lucevent.newsup.data.util;

import com.lucevent.newsup.data.reader.UserSiteReader;

public class UserSite extends Site {

    public final String icon;

    public final String rssUrl;

    public UserSite(int code, String name, int color, String url, int info, String rssUrl, String icon)
    {
        super(code, name, color, url, info, Sections.class, UserSiteReader.class);
        this.rssUrl = rssUrl;
        this.icon = icon;
    }

    @Override
    public Sections getSections()
    {
        if (sections == null) {
            sections = new Sections();
            sections.add(new Section("Main", rssUrl, 0));
        }
        return sections;
    }

}
