package com.lucevent.newsup.debugbackend.data;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class Log {

    @Id
    private Long id;

    @Index
    public Long taskId;

    @Unindex
    public int order;

    @Unindex
    public String data;

    public Log()
    {
    }

}
