package com.lucevent.newsup.debugbackend.kernel;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.debugbackend.data.Database;
import com.lucevent.newsup.debugbackend.data.Task;
import com.lucevent.newsup.debugbackend.util.ReportCallback;

import org.jsoup.nodes.Document;


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
        Evaluator evaluator = new Evaluator();

        Task task = db.getCurrentTask(evaluator.size());

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
                    }
                    int[] partialValues = evaluator.evaluate(news, db);
                    for (int t = 0; t < tempValues.length; t++)
                        tempValues[t] += partialValues[t];

                    task.siteNumNews += news.size();
                    for (int i = 0; i < tempValues.length; i++)
                        task.siteTestResults[i] += tempValues[i];
                }
            }

            // Log save
            StringBuilder sb = new StringBuilder();
            sb.append("{").append(site.name).append(",").append(task.siteNumNews);
            for (int i = 0; i < task.siteTestResults.length; i++) {
                sb.append(",").append(task.siteTestResults[i]);
            }
            sb.append("}\n");

            String partialReport = sb.toString();
            db.saveLog(task.id, task.currentEvaluatingSite, partialReport);

            if (task.siteNumNews == 0 || (task.siteNumNews / 2) < task.siteTestResults[0])
                urgentCallback.report(partialReport);

            task.totalNumNews += task.siteNumNews;
            for (int t = 0; t < task.totalTestResults.length; t++) {
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
                sb.append("\t").append(res).append(" ").append(evaluator.getDescription(i)).append("\n");
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

    private class Evaluator {

        private static final int D_EMPTY = 0;
        private static final int D_HS = 1;
        private static final int D_SCRIPTS = 2;
        private static final int D_LINKS = 3;
        private static final int D_STYLES_TAGS = 4;
        private static final int D_STYLE_ATTR = 5;
        private static final int D_A_OBJECT = 6;
        private static final int D_COMMENTS = 7;

        private final String[] descriptions = new String[]{
                "estan vac\u00EDas",     // 52
                "contienen h1/h2",       //*0
                "tienen scripts",        // 0
                "con links sin HTTP",    //*19
                "tienen style tags",     //*52
                "tienen style attrs",    // 313
                "tienen <a><object></a>",//*14
                "tienen comentarios"     //*1
        };

        public int[] evaluate(NewsArray news, Database db)
        {
            int[] res = new int[descriptions.length];
            for (int i = 0; i < res.length; i++)
                res[i] = 0;

            for (News n : news) {
                Document doc = org.jsoup.Jsoup.parse(n.content);

                if (evaluateEmpty(n.content)) {
                    res[D_EMPTY]++;
                }
                if (evaluateHs(doc)) {
                    db.saveErrorOn(n);
                    res[D_HS]++;
                }
                if (evaluateScripts(doc)) {
                    res[D_SCRIPTS]++;
                }
                if (evaluateLinks(n.content)) {
                    db.saveErrorOn(n);
                    res[D_LINKS]++;
                }
                if (evaluateStyleTag(doc)) {
                    db.saveErrorOn(n);
                    res[D_STYLES_TAGS]++;
                }
                if (evaluateStyleAttr(doc)) {
                    res[D_STYLE_ATTR]++;
                }
                if (evaluateAWithObject(doc)) {
                    db.saveErrorOn(n);
                    res[D_A_OBJECT]++;
                }
                if (evaluateComments(n.content)) {
                    db.saveErrorOn(n);
                    res[D_COMMENTS]++;
                }
            }
            return res;
        }

        private boolean evaluateHs(Document doc)
        {
            return !doc.select("h1,h2").isEmpty();
        }

        private boolean evaluateEmpty(String content)
        {
            return content == null || content.isEmpty();
        }

        private boolean evaluateScripts(Document doc)
        {
            return !doc.select("script").isEmpty();
        }

        private boolean evaluateLinks(String content)
        {
            return content.contains("=\"//") || content.contains("='//");
        }

        private boolean evaluateStyleTag(Document doc)
        {
            return !doc.select("style").isEmpty();
        }

        private boolean evaluateStyleAttr(Document doc)
        {
            return !doc.select("[style]").isEmpty();
        }

        private boolean evaluateAWithObject(Document doc)
        {
            return !doc.select("a:has(img,iframe,video,figure,picture)").isEmpty();
        }

        private boolean evaluateComments(String content)
        {
            return content.contains("<!--");
        }

        int size()
        {
            return descriptions.length;
        }

        String getDescription(int position)
        {
            return descriptions[position];
        }

    }

}
