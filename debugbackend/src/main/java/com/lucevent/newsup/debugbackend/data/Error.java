package com.lucevent.newsup.debugbackend.data;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class Error {

    @Id
    private Long id;

    @Index
    public int n_site;

    @Unindex
    public String n_link;

    @Unindex
    public String n_content;


    public Error()
    {
    }

}