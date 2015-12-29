package com.backend;

import com.backend.kernel.list.BE_SiteList;

public class Data {

    public static BE_SiteList sites;

    public Data() {
        if (sites == null) {
            sites = new BE_SiteList();
        }
    }

}
