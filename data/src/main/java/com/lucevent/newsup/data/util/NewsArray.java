package com.lucevent.newsup.data.util;

import java.util.ArrayList;

public class NewsArray extends ArrayList<News> {

    public NewsArray()
    {
        super();
    }

    public void setCode(int code)
    {
        for (News N : this)
            N.site_code = code;
    }

}
