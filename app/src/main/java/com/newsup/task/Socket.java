package com.newsup.task;

public interface Socket extends TaskMessage {

    void message(int taskMessage, Object dataAttached);
}
