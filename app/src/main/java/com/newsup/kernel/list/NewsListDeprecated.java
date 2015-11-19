package com.newsup.kernel.list;

import com.newsup.kernel.News;

import java.util.ArrayList;

public class NewsListDeprecated extends ArrayList<News> {

    private static final long serialVersionUID = -4295930172654754706L;

    public NewsListDeprecated() {
        super();
    }

    public NewsListDeprecated(NewsMap newsmap) {
        super(newsmap);
    }

    public NewsListDeprecated(int size) {
        super(size);
    }
}
