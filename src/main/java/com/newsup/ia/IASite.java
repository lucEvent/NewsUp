package com.newsup.ia;


import java.util.ArrayList;

public class IASite {

    public final int code;

    public long last_access;

    public ArrayList<Long> accesses;

    public IASite(int code) {
        this.code = code;
        last_access = 0;
        accesses = new ArrayList<Long>();
    }

    public void registerAccess() {
        last_access = System.currentTimeMillis();
        accesses.add(last_access);
    }
}
