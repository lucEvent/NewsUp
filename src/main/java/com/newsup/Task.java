package com.newsup;

public class Task extends Thread {

    public Object data;

    public Task(Object data) {
        super();
        this.data = data;
    }

}
