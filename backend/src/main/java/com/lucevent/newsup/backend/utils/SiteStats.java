package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class SiteStats {

    @Unindex
    public String siteName;

    @Id
    @Index
    public long siteCode;

    @Unindex
    public int nAccesses;

    @Unindex
    public int nNewsRead;

    @Unindex
    public long lastAccess;

    @Unindex
    public String lastIp;

    @Unindex
    public String fromVersion;

    public SiteStats()
    {
    }

}
