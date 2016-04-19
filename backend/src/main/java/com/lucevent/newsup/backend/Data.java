package com.lucevent.newsup.backend;

import com.lucevent.newsup.data.Sites;

public class Data {

    public static Sites sites;

    public Data()
    {
        if (sites == null)
            sites = new Sites((String[]) null);
    }

}
