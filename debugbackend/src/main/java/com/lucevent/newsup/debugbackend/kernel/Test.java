package com.lucevent.newsup.debugbackend.kernel;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.debugbackend.data.Database;
import com.lucevent.newsup.debugbackend.data.Task;
import com.lucevent.newsup.debugbackend.util.ReportCallback;


public class Test {

    private Database db;

    public Test()
    {
        db = new Database();
    }

    /**
     * @return report of the task performed
     */
    public String doTest(ReportCallback urgentCallback)
    {
        Sites sites = Sites.getDefault(true);
        Evaluation[] evals = new Evaluation[]{WITHCONTENT, TH2, SCRIPTS, LINKS, STYLES};

        Task task = db.getCurrentTask(evals.length);

        db.newRound(task);

        if (task.currentEvaluatingSite < sites.size()) {
            Site site = sites.get(task.currentEvaluatingSite);

            for (; task.currentEvaluatingSection < site.getSections().size(); task.currentEvaluatingSection++, db.save(task, true)) {
                Section section = site.getSections().get(task.currentEvaluatingSection);
                if (section.url != null) {

                    int[] tempValues = new int[task.siteTestResults.length];
                    for (int i = 0; i < tempValues.length; i++)
                        tempValues[i] = 0;

                    NewsArray news = site.readNewsHeaders(new int[]{site.getSections().get(task.currentEvaluatingSection).code});
                    for (News N : news) {
                        if (N.content == null || N.content.isEmpty()) {
//                            try {
//                                Thread.sleep(500);
//                            } catch (InterruptedException ignored) {
//                            }
                            site.readNewsContent(N);
                        }
                        for (int t = 0; t < evals.length; t++)
                            if (evals[t].evaluate(N, db))
                                tempValues[t]++;
                    }

                    task.siteNumNews += news.size();
                    for (int i = 0; i < tempValues.length; i++)
                        task.siteTestResults[i] += tempValues[i];
                }
            }

            // Log save
            StringBuilder sb = new StringBuilder();
            sb.append("\tResultados para ").append(site.name).append("\n");
            sb.append("\t\t").append(task.siteNumNews).append(" noticias\n");
            for (int i = 0; i < task.siteTestResults.length; i++) {
                int res = task.siteTestResults[i];
                if (res > 0) {
                    sb.append("\t\t").append(res).append(" ").append(evals[i].description()).append("\n");
                }
            }
            sb.append("_________________________________________________\n\n");

            String partialReport = sb.toString();
            db.saveLog(task.id, task.currentEvaluatingSite, partialReport);

            if (task.siteNumNews == 0 || (task.siteNumNews / 2) < task.siteTestResults[0])
                urgentCallback.report(partialReport);

            task.totalNumNews += task.siteNumNews;
            for (int t = 0; t < evals.length; t++) {
                task.totalTestResults[t] += task.siteTestResults[t];
                task.siteTestResults[t] = 0;
            }
            task.currentEvaluatingSite++;
            task.currentEvaluatingSection = 0;
            task.siteNumNews = 0;
            db.save(task, false);

            return null;
        }

        // Total results log save
        StringBuilder sb = new StringBuilder();
        sb.append("** Resultados totales **\n");
        sb.append("\t").append(task.totalNumNews).append(" noticias\n");
        for (int i = 0; i < task.totalTestResults.length; i++) {
            int res = task.totalTestResults[i];
            if (res > 0) {
                sb.append("\t").append(res).append(" ").append(evals[i].description()).append("\n");
            }
        }
        db.saveLog(task.id, task.currentEvaluatingSite, sb.toString());

        db.finish(task);

        return db.getFullReport(task);
    }

    public void clearLogs()
    {
        db.clearLogs();
    }

    private static final Evaluation TH2 = new Evaluation() {
        @Override
        public String description()
        {
            return "contienen h1/h2";
        }

        @Override
        public boolean evaluate(News news, Database db)
        {
            boolean evaluation = news.content.contains("<h2>") | news.content.contains("<h1>");
            if (evaluation) {
                db.saveErrorOn(news);
            }
            return evaluation;
        }
    };

    private static final Evaluation WITHCONTENT = new Evaluation() {
        @Override
        public String description()
        {
            return "estan vac\u00EDas";
        }

        @Override
        public boolean evaluate(News news, Database db)
        {
            return news.content == null || news.content.isEmpty();
        }
    };

    private static final Evaluation SCRIPTS = new Evaluation() {
        @Override
        public String description()
        {
            return "tienen scripts";
        }

        @Override
        public boolean evaluate(News news, Database db)
        {
            return news.content.contains("</script>");
        }
    };

    private static final Evaluation LINKS = new Evaluation() {
        @Override
        public String description()
        {
            return "con links sin HTTP";
        }

        @Override
        public boolean evaluate(News news, Database db)
        {
            return news.content.contains("=\"//") || news.content.contains("='//");
        }
    };

    private static final Evaluation STYLES = new Evaluation() {
        @Override
        public String description()
        {
            return "tienen styles";
        }

        @Override
        public boolean evaluate(News news, Database db)
        {
            return news.content.contains("</style>") || news.content.contains("style=\"");
        }
    };

}
