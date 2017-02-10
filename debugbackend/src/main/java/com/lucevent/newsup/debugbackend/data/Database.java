package com.lucevent.newsup.debugbackend.data;

import java.util.Comparator;
import java.util.TreeSet;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class Database {

    public Database()
    {
    }

    public TaskState getCurrentTask()
    {
        TaskState res = ofy().load().type(TaskState.class)
                .filter("finishTime", -1L)
                .first().now();

        if (res == null) {

            res = new TaskState();
            res.startTime = System.currentTimeMillis();
            res.finishTime = -1L;
            res.rounds = 0;
            ofy().save().entity(res).now();

        }
        return res;
    }

    public TaskData getTaskData(TaskState taskState, int evaluations)
    {
        TaskData res = ofy().load().type(TaskData.class).id(taskState.startTime).now();

        if (res == null) {

            res = new TaskData();
            res.taskId = taskState.startTime;
            res.currentEvaluatingSite = 0;
            res.currentEvaluatingSection = 0;
            res.totalNumNews = 0;
            res.totalTestResults = new int[evaluations];
            res.siteNumNews = 0;
            res.siteTestResults = new int[evaluations];

            for (int i = 0; i < evaluations; i++) {
                res.totalTestResults[i] = 0;
                res.siteTestResults[i] = 0;
            }

            ofy().save().entity(res).now();

        }
        return res;
    }

    public void newRound(TaskState taskState)
    {
        taskState.rounds++;
        ofy().save().entity(taskState).now();
    }

    public void finish(TaskState taskState)
    {
        taskState.finishTime = System.currentTimeMillis();
        ofy().save().entity(taskState).now();
    }

    public void save(TaskData data, boolean deferred)
    {
        if (deferred)
            ofy().defer().save().entity(data);

        else
            ofy().save().entity(data).now();
    }

    public void saveLog(TaskState taskState, int order, String data)
    {
        Log log = new Log();
        log.taskId = taskState.startTime;
        log.order = order;
        log.data = data;

        ofy().save().entity(log).now();
    }

    public String getFullReport(TaskState taskState)
    {
        TreeSet<Log> logs = new TreeSet<>(new Comparator<Log>() {
            @Override
            public int compare(Log o1, Log o2)
            {
                return (o1.order < o2.order) ? -1 : ((o1.order == o2.order) ? 0 : 1);
            }
        });
        logs.addAll(ofy().load().type(Log.class)
                .filter("taskId", taskState.startTime)
                .list());

        StringBuilder res = new StringBuilder();
        for (Log log : logs)
            res.append(log.data);

        return res.toString();
    }

}
