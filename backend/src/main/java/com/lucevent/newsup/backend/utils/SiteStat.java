package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class SiteStat {

    @Unindex
    public String siteName;

    @Id
    public long siteCode;

    @Unindex
    public int nAccesses;

    @Unindex
    public long lastAccess;

    @Unindex
    public String lastIp;

    public SiteStat()
    {
    }

}
