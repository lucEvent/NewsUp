package com.lucevent.newsup.debugbackend.data;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Bug {

    @Id
    public Long id;

    @Index
    public int site_code;

    public long time;

    public String description;

    public String link;

    public String content;

    public Bug()
    {
    }

}
