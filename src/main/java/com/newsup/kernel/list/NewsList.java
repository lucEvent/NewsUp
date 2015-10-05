package com.newsup.kernel.list;

import java.util.ArrayList;

import com.newsup.kernel.News;

public class NewsList extends ArrayList<News> {

    private static final long serialVersionUID = -4295930172654754706L;

    public NewsList() {
        super();
    }

    public NewsList(int capacity) {
        super(capacity);
    }

}
