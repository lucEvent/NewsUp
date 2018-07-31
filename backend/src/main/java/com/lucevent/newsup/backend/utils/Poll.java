package com.lucevent.newsup.backend.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Poll {

    @Id
    @Index
    public Long id;

    public int answer_1_code;
    public int answer_2_code;
    public int answer_3_code;

    public int answer_1_counter;
    public int answer_2_counter;
    public int answer_3_counter;

}
