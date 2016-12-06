package com.lucevent.newsup.debugbackend.kernel;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.debugbackend.data.Database;
import com.lucevent.newsup.debugbackend.data.TaskData;
import com.lucevent.newsup.debugbackend.data.TaskState;


public class Test {

    private Database db;

    public Test()
    {
        db = new Database();
    }

    /**
     * @return id of the task performed
     */
    public String doTest()
    {
        Sites sites = Sites.getDefault(true);
        Evaluation[] evals = new Evaluation[]{WITHCONTENT, TH2};

        TaskState taskState = db.getCurrentTask();
        TaskData data = db.getTaskData(taskState, evals.length);

        db.newRound(taskState);

        for (; data.currentEvaluatingSite < sites.size(); data.currentEvaluatingSite++, db.save(data, true)) {
            Site site = sites.get(data.currentEvaluatingSite);

            for (; data.currentEvaluatingSection < site.getSections().size(); data.currentEvaluatingSection++, db.save(data, true)) {
                Section section = site.getSections().get(data.currentEvaluatingSection);
                if (section.url != null) {

                    NewsArray news = site.readNewsHeaders(new int[]{data.currentEvaluatingSection});
                    for (News N : news) {
                        if (N.content == null || N.content.isEmpty()) {
//                            try {
//                                Thread.sleep(500);
//                            } catch (InterruptedException ex) {
//                                System.out.println("InterruptedException: " + ex.toString());
//                            }
                            site.readNewsContent(N);
                        }
                        for (int t = 0; t < evals.length; t++) {
                            Evaluation test = evals[t];
                            if (test.evalutate(N)) {
                                data.siteTestResults[t]++;
                            }
                        }
                    }
                    data.siteNumNews += news.size();
                }
            }

            // Log save
            StringBuilder sb = new StringBuilder();
            sb.append("** ").append(site.name).append(" **\n");
            sb.append("\n\tResultados:\n");
            sb.append("\t\t").append(data.siteNumNews).append(" noticias\n");
            for (int i = 0; i < data.siteTestResults.length; i++) {
                String dscr = evals[i].description();
                int res = data.siteTestResults[i];
                int negRes = data.siteNumNews - res;
                sb.append("\t\t").append(res).append(" ").append(dscr).append(" (").append(negRes).append(" no)\n");
            }
            sb.append("_________________________________________________\n\n");

            db.saveLog(taskState, data.currentEvaluatingSite, sb.toString());

            data.totalNumNews += data.siteNumNews;
            for (int t = 0; t < evals.length; t++) {
                data.totalTestResults[t] += data.siteTestResults[t];
                data.siteTestResults[t] = 0;
            }
            data.currentEvaluatingSection = 0;
            data.siteNumNews = 0;
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

    private static final Evaluation TH2 = new Evaluation() {
        @Override
        public String description()
        {
            return "contienen <h2>";
        }

        @Override
        public boolean evalutate(News news)
        {
            return news.content.contains("<h2>");
        }
    };

    private static final Evaluation WITHCONTENT = new Evaluation() {
        @Override
        public String description()
        {
            return "tienen contenido";
        }

        @Override
        public boolean evalutate(News news)
        {
            return news.content != null && !news.content.isEmpty();
        }
    };

}
