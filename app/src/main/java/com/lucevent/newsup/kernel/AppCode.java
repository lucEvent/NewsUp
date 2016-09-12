package com.lucevent.newsup.kernel;

public interface AppCode {

    int REQUEST_PERMISSION_WRITE_IN_STORAGE = 200;
    int REQUEST_PERMISSION_READ_PHONE_STATE = 201;

    int ERROR = -9;

    int NEWS_MAP_READ = 10;
    int NEWS_MAP_FRAGMENT_READ = 11;
    int NEWS_LOADED = 12;
    int NO_INTERNET = 13;

    int REQUEST_ADD_CONTENT = 30;

    int STATISTICS = 50;

    String SEND_SITE_CODE = "site_code";
    String SEND_NEWS = "news";
    String SEND_NEWS_IDS = "news_ids";
    String SEND_DOWNLOAD_SCHEDULE = "download_schedule";
    String SEND_PURPOSE = "purpose";
    String SEND_REQUEST_CODE = "req_code";

    String ACTION = "action";
    String RESTART = "restart";

}
