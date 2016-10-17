package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class SiteStats {

    @Unindex
    String siteName;

    @Id
    @Index
    long siteCode;

    @Unindex
    int nAccesses;

    @Unindex
    int nNewsRead;

    @Unindex
    long lastAccess;

    @Unindex
    String lastIp;

    @Unindex
    String fromVersion;

    SiteStats()
    {
    }

}
