package com.lucevent.newsup.kernel.stats;

public class SiteStat {

    public final String siteName;

    public final int siteCode;

    public final int nAccesses;

    public final long lastAccess;

    public final String lastIp;

    public SiteStat(String siteName, int siteCode, int nAccesses, long lastAccess, String lastIp)
    {
        this.siteName = siteName;
        this.siteCode = siteCode;
        this.nAccesses = nAccesses;
        this.lastAccess = lastAccess;
        this.lastIp = lastIp;
    }

}
