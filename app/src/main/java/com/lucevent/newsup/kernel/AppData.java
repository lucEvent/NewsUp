package com.lucevent.newsup.kernel;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.SitesMap;
import com.lucevent.newsup.data.sports.Sports;
import com.lucevent.newsup.data.sports.util.Sport;
import com.lucevent.newsup.data.util.Site;

public class AppData {

    public static Sites sites;
    public static SitesMap sitesOrderedByName;

    public static Sports sports;

    public static Site getSiteByCode(int site_code)
    {
        return sites.getSiteByCode(site_code);
    }

    public static Sport getSportByCode(int site_code)
    {
        return sports.getSportByCode(site_code);
    }

}
