package com.lucevent.newsup.kernel;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Site;

public class AppData {

    public static Sites sites;

    public static Site getSiteByCode(int code)
    {
        return sites.getSiteByCode(code);
    }

    public static Sites getSites(int[] codes)
    {
        Sites res = new Sites();
        for (int code : codes)
            res.add(sites.getSiteByCode(code));

        return res;
    }

}
