package com.lucevent.newsup.debugbackend.kernel;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.debugbackend.data.Database;
import com.lucevent.newsup.debugbackend.data.TaskData;
import com.lucevent.newsup.debugbackend.data.TaskState;
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

        TaskState taskState = db.getCurrentTask();
        TaskData data = db.getTaskData(taskState, evals.length);

        db.newRound(taskState);

        if (data.currentEvaluatingSite < sites.size()) {
            Site site = sites.get(data.currentEvaluatingSite);

            for (; data.currentEvaluatingSection < site.getSections().size(); data.currentEvaluatingSection++, db.save(data, true)) {
                Section section = site.getSections().get(data.currentEvaluatingSection);
                if (section.url != null) {

                    int[] tempValues = new int[data.siteTestResults.length];
                    for (int i = 0; i < tempValues.length; i++)
                        tempValues[i] = 0;

                    NewsArray news = site.readNewsHeaders(new int[]{data.currentEvaluatingSection});
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

                    data.siteNumNews += news.size();
                    for (int i = 0; i < tempValues.length; i++)
                        data.siteTestResults[i] += tempValues[i];
                }
            }

            // Log save
            StringBuilder sb = new StringBuilder();
            sb.append("\tResultados para ").append(site.name).append("\n");
            sb.append("\t\t").append(data.siteNumNews).append(" noticias\n");
            for (int i = 0; i < data.siteTestResults.length; i++) {
                String dscr = evals[i].description();
                int res = data.siteTestResults[i];
                int negRes = data.siteNumNews - res;
                sb.append("\t\t").append(res).append(" ").append(dscr).append(" (").append(negRes).append(" no)\n");
            }
            sb.append("_________________________________________________\n\n");

            String partialReport = sb.toString();
            db.saveLog(taskState, data.currentEvaluatingSite, partialReport);

            if ((data.siteNumNews / 2) > data.siteTestResults[0] || data.siteNumNews == 0)
                urgentCallback.report(partialReport);

            data.totalNumNews += data.siteNumNews;
            for (int t = 0; t < evals.length; t++) {
                data.totalTestResults[t] += data.siteTestResults[t];
                data.siteTestResults[t] = 0;
            }
            data.currentEvaluatingSite++;
            data.currentEvaluatingSection = 0;
            data.siteNumNews = 0;
            db.save(data, false);

            return null;
        }

        // Total results log save
        StringBuilder sb = new StringBuilder();
        sb.append("** Resultados totales **\n");
        sb.append("\t").append(data.totalNumNews).append(" noticias\n");
        for (int i = 0; i < data.totalTestResults.length; i++) {
            String dscr = evals[i].description();
            int res = data.totalTestResults[i];
            int negRes = data.totalNumNews - res;
            sb.append("\t").append(res).append(" ").append(dscr).append(" (").append(negRes).append(" no)\n");
        }
        db.saveLog(taskState, data.currentEvaluatingSite, sb.toString());

        db.finish(taskState);

        return db.getFullReport(taskState);
    }

    public void clearLogs()
    {
        db.clearLogs();
    }

    private static final Evaluation TH2 = new Evaluation() {
        @Override
        public String description()
        {
            return "contienen <h2>|<h1>";
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
            return "tienen contenido";
        }

        @Override
        public boolean evaluate(News news, Database db)
        {
            return news.content != null && !news.content.isEmpty();
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
            return "con links partidos";
        }

        @Override
        public boolean evaluate(News news, Database db)
        {
            return (news.content.contains("src=\"/") || news.content.contains("href=\"/")) && !news.content.contains("<base ");
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
