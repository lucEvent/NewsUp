package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class RequestedSiteNotFound {

    @Id
    @Index
    private Long id;

    @Unindex
    public String request;

    @Unindex
    public int state;

}
