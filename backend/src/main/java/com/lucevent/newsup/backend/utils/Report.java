package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class Report {

    @Id
    @Index
    private Long id;

    @Unindex
    public Long time;

    @Unindex
    public String ip;

    @Unindex
    public String email;

    @Unindex
    public String message;

}
