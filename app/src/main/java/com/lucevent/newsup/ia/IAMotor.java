package com.lucevent.newsup.ia;

import java.util.Comparator;

public class IAMotor implements Comparator<IASite> {

    long SITE_MAX_TIME_SINCE_LAST_ACCESS = 7 * 24 * 60 * 60 * 1000;

/*
    private final KernelManager publicdata;
    private final IAStorage storage;
    private final TreeSet<IASite> ositeList;
*/

    public IAMotor()
    {
    }

    @Override
    public int compare(IASite s1, IASite s2)
    {
        int res = Integer.compare(s1.accesses.size(), s2.accesses.size());
        return res != 0 ? res : Long.compare(s1.last_access, s2.last_access);
    }

}
