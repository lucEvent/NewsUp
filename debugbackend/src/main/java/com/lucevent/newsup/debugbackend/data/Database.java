package com.lucevent.newsup.debugbackend.data;

import com.googlecode.objectify.Key;
import com.lucevent.newsup.data.util.News;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class Database {

    public Database()
    {
    }

    public Task getCurrentTask(int default_test_results_size)
    {
        Task res = ofy().load().type(Task.class)
                .filter("finishTime", -1L)
                .first().now();

        if (res == null) {

            res = new Task();
            res.startTime = System.currentTimeMillis();
            res.finishTime = -1L;
            res.rounds = 0;
            res.currentEvaluatingSite = 0;
            res.currentEvaluatingSection = 0;
            res.totalNumNews = 0;
            res.totalTestResults = new int[default_test_results_size];
            res.siteNumNews = 0;
            res.siteTestResults = new int[default_test_results_size];

            for (int i = 0; i < default_test_results_size; i++) {
                res.totalTestResults[i] = 0;
                res.siteTestResults[i] = 0;
            }

            ofy().save().entity(res).now();
        }
        return res;
    }

    public void newRound(Task task)
    {
        task.rounds++;
        ofy().save().entity(task).now();
    }

    public void finish(Task task)
    {
        task.finishTime = System.currentTimeMillis();
        ofy().save().entity(task).now();
    }

    public void save(Task data, boolean deferred)
    {
        if (deferred)
            ofy().defer().save().entity(data);

        else
            ofy().save().entity(data).now();
    }

    public void saveLog(long task_id, int order, String data)
    {
        Log log = new Log();
        log.taskId = task_id;
        log.order = order;
        log.data = data;

        ofy().save().entity(log).now();
    }

    public void clearLogs()
    {
        List<Key<Log>> keys = ofy().load().type(Log.class).keys().list();
        ofy().delete().keys(keys).now();
    }

    public String getFullReport(Task task)
    {
        TreeSet<Log> logs = new TreeSet<>(new Comparator<Log>() {
            @Override
            public int compare(Log o1, Log o2)
            {
                return (o1.order < o2.order) ? -1 : ((o1.order == o2.order) ? 0 : 1);
            }
        });
        logs.addAll(ofy().load().type(Log.class)
                .filter("taskId", task.id)
                .list());

        StringBuilder res = new StringBuilder();
        for (Log log : logs)
            res.append(log.data);

        return res.toString();
    }

    public void saveErrorOn(News news)
    {
        Error error = new Error();
        error.n_link = news.link;
        error.n_content = news.content;
        error.n_site = news.site_code;

        ofy().save().entity(error).now();
    }

    public ArrayList<Error> getErrors()
    {
        ArrayList<Error> res = new ArrayList<>();
        res.addAll(ofy().load().type(Error.class).list());
        return res;
    }

    public TreeSet<Task> getTasks()
    {
        TreeSet<Task> res = new TreeSet<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2)
            {
                return -Long.compare(o1.startTime, o2.startTime);
            }
        });
        res.addAll(ofy().load().type(Task.class).list());
        return res;
    }

}
