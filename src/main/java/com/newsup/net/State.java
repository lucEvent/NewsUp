package com.newsup.net;

public interface State {

    int NO_INTERNET = -2;
    int ERROR = -1;
    int NEWS_READ = 0;
    int NEWS_READ_CONTENT = 1;
    int NEWS_READ_HISTORY = 2;
    int SECTION_BEGIN = 3;
    int WORK_DONE = 4;
    int NEWS_READ_BOOKMARKS = 5;

    int ACTION_CLOSE_NEWS = 6;

}
