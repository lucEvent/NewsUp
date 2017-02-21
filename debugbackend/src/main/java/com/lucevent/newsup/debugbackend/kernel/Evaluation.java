package com.lucevent.newsup.debugbackend.kernel;

import com.lucevent.newsup.debugbackend.data.Database;

public interface Evaluation {

    String description();

    boolean evaluate(com.lucevent.newsup.data.util.News news, Database db);

}
