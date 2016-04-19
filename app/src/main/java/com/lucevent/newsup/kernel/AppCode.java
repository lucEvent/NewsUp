package com.lucevent.newsup.kernel;

public interface AppCode {

    int NO_INTERNET = -12;
    int ERROR = -10;

    int NEWS_MAP_READ = 10;
    int NEWS_MAP_FRAGMENT_READ = 11;

    int ACTION_OPEN_NEWS = 20;
    int ACTION_REFRESH_LIST = 21;
    int ACTION_UPDATE_FAVORITES = 22;
    int ACTION_BOOKMARK_UNBOOKMARK = 23;


    int SELECTED_SECTION = 30;
    int SELECTED_SECTIONS = 31;
    int SELECTED_SITE_CODES = 32;

    int DOWNLOAD_SCHEDULE = 40;
    int REMOVE_SCHEDULE = 41;

    String SEND_SITE_CODE = "site_code";
    String SEND_NEWS = "news";
    String SEND_NEWS_IDS = "news_ids";
    String SEND_DOWNLOAD_SCHEDULE = "download_schedule";

    String ACTION = "action";

}
