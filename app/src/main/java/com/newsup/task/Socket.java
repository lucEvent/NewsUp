package com.newsup.task;

public interface Socket extends SocketMessage {

    void message(int taskMessage, Object dataAttached);

}
