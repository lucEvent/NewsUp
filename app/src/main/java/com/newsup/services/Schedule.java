package com.newsup.services;

public class Schedule implements Comparable<Schedule> {

    public long time;
    public Object object;

    public Schedule() {
    }

    @Override
    public int compareTo(Schedule another) {
        return this.time < another.time ? -1 : (this.time == another.time ? 0 : 1);
    }
}
