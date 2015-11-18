package com.newsup.kernel.list;

import com.newsup.kernel.News;

import java.util.ArrayList;

public class NewsList extends ArrayList<News> {

    private static final long serialVersionUID = -4295930172654754706L;

    public NewsList() {
        super();
    }

    public NewsList(NewsMap newsmap) {
        super(newsmap);
    }

    public NewsList(int size) {
        super(size);
    }
}
