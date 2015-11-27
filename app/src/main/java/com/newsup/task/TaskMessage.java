package com.newsup.task;

public interface TaskMessage {

    int NO_INTERNET = -2;
    int ERROR = -1;
    int NEWS_READ = 0;
    int NEWS_READ_HISTORY = 1;
    int NEWS_READ_BOOKMARKS = 2;
    int ACTION_REFRESH_LIST = 3;
    int OPEN_NEWS = 4;

    int SECTION_SELECTED = 5;
}
