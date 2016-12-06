package com.lucevent.newsup.kernel.stats;

public class SiteStat {

    public final String siteName;

    public final int siteCode;

    public final int nAccesses;

    public final int readings;

    public final long lastAccess;

    public final String version;

    public SiteStat(String siteName, int siteCode, int nAccesses, int readings, long lastAccess, String version)
    {
        this.siteName = siteName;
        this.siteCode = siteCode;
        this.nAccesses = nAccesses;
        this.readings = readings;
        this.lastAccess = lastAccess;
        this.version = version;
    }

}
