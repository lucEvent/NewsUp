package com.lucevent.newsup.debugbackend.data;

import com.googlecode.objectify.Key;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.Site;

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

    public void savePartialResult(PartialTestResult ptr)
    {
        ofy().save().entity(ptr).now();
    }

    public void clearTestCache()
    {
        List<Key<PartialTestResult>> keys = ofy().load().type(PartialTestResult.class).keys().list();
        ofy().delete().keys(keys).now();
    }

    public List<PartialTestResult> getPartialTestResults(Long task_id)
    {
        return ofy().load().type(PartialTestResult.class)
                .filter("taskId", task_id)
                .list();
    }

    public void saveBugOn(News news, String description)
    {
        Bug bug = new Bug();
        bug.site_code = news.site_code;
        bug.time = System.currentTimeMillis();
        bug.description = description;
        bug.link = "";
        bug.content = news.content;

        ofy().save().entity(bug).now();
    }

    public void saveBug(Site s, String description)
    {
        Bug bug = new Bug();
        bug.site_code = s.code;
        bug.time = System.currentTimeMillis();
        bug.description = description;
        bug.link = "";
        bug.content = "";

        ofy().save().entity(bug).now();
    }

    public ArrayList<Bug> getBugs()
    {
        return new ArrayList<>(ofy().load().type(Bug.class).list());
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
