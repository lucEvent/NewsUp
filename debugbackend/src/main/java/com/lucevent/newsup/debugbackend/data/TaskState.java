package com.lucevent.newsup.debugbackend.data;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;

@Entity
public class TaskState {

    @Id
    @Index
    long startTime;

    @Index
    long finishTime;

    @Unindex
    public int rounds;

    public TaskState()
    {
    }

}
