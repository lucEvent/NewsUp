package com.lucevent.newsup.kernel;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Site;

public class AppData {

    public static Sites sites;

    public static Site getSiteByCode(int site_code)
    {
        return sites.getSiteByCode(site_code);
    }

}
