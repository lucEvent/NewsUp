package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class RequestedSite {

    @Id
    @Index
    public long code;

    @Unindex
    public String original_request;

    @Unindex
    public String name;

    @Unindex
    public String url;

    @Unindex
    public String rss_url;

    @Unindex
    public String icon_url;

    @Unindex
    public int info;

    @Unindex
    public int color;

}
