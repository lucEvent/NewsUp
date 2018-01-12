package com.lucevent.newsup.kernel;

public interface AppCode {

    int REQUEST_PERMISSION_WRITE_IN_STORAGE = 200;
    int REQUEST_PERMISSION_READ_PHONE_STATE = 201;

    int ERROR = -9;

    int NEWS_COLLECTION = 8;

    int NEWS_LOADED = 11;
    int NO_INTERNET = 12;
    int REPORT_SEND_OK = 13;

    int REQUEST_ADD_CONTENT = 30;

    int STATISTICS = 50;

    String SITE_CODE = "site_code";
    String SOURCES = "sources";
    String DOWNLOAD_SCHEDULE = "d.schedule";
    String PURPOSE = "purpose";
    String REQUEST_CODE = "r.code";
    String SELECTED = "selected";
    String EVENT_CODE = "e.code";
    String STRING_FILTERS = "fts";

    String ACTION = "action";
    String RESTART = "restart";

}
