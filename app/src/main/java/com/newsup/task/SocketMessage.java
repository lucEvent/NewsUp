package com.newsup.task;

public interface SocketMessage {

    int NO_INTERNET = -2;

    int ERROR = -1;

    int NEWS_READ = 0;
    int HISTORY_READ = 1;
    int BOOKMARKS_READ = 2;

    int ACTION_REFRESH_LIST = 3;
    int ACTION_OPEN_NEWS = 4;

    int SELECTED_SECTION = 5;
    int SELECTED_SECTIONS = 6;
    int SELECTED_SITE_CODES = 7;

    int DOWNLOAD_SCHEDULE = 8;
    int REMOVE_SCHEDULE = 9;

}
