package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class SiteStats {

    @Id
    @Index
    public long siteCode;

    public int totalRequests, monthRequests, readings;

    public long lastRequest;

    public String siteName, lastIp, fromVersion;

    public SiteStats()
    {
    }

}
