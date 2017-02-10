package com.lucevent.newsup.debugbackend.kernel;

public interface Evaluation {

    String description();

    boolean evaluate(com.lucevent.newsup.data.util.News news);

}
