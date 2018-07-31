package com.lucevent.newsup.debugbackend.kernel;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.debugbackend.data.Database;
import com.lucevent.newsup.debugbackend.data.PartialTestResult;
import com.lucevent.newsup.debugbackend.data.Task;
import com.lucevent.newsup.debugbackend.util.ReportCallback;

import org.jsoup.nodes.Document;

import java.util.Comparator;
import java.util.TreeSet;

public class Test {

    private Database db;

    public Test() {
        db = new Database();
    }

    /**
     * @return report of the task performed
     */
    public String doTest(ReportCallback urgentCallback) {
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

            // Saving results for current Site
            PartialTestResult ptr = new PartialTestResult();
            ptr.taskId = task.id;
            ptr.siteCode = site.code;
            ptr.numNews = task.siteNumNews;
            ptr.testResults = task.siteTestResults;
            db.savePartialResult(ptr);

            if (task.siteNumNews == 0 || (task.siteNumNews / 2) < task.siteTestResults[0])
                urgentCallback.report(site.name + " | " + task.siteNumNews + " news read / " + task.siteTestResults[0] + " without content");

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

        db.finish(task);


        TreeSet<PartialTestResult> partials = new TreeSet<>(new Comparator<PartialTestResult>() {
            @Override
            public int compare(PartialTestResult o1, PartialTestResult o2) {
                return Integer.compare(o1.siteCode, o2.siteCode);
            }
        });
        partials.addAll(db.getPartialTestResults(task.id));

        // Creating report
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < evaluator.size(); ++i) {
            sb.append("** ").append(evaluator.getDescription(i)).append(" **\n");
            for (PartialTestResult ptr : partials) {
                if (i < ptr.testResults.length && ptr.testResults[i] > 0)
                    sb.append("\t *").append(sites.getSiteByCode(ptr.siteCode).name).append(" (").append(ptr.testResults[i]).append(")\n");
            }
        }
        sb.append("\n** Resultados totales **\n");
        sb.append("\t").append(task.totalNumNews).append(" noticias\n");
        for (int i = 0; i < task.totalTestResults.length; i++) {
            int res = task.totalTestResults[i];
            if (res > 0) {
                sb.append("\t").append(res).append(" ").append(evaluator.getDescription(i)).append("\n");
            }
        }

        return sb.toString();
    }

    public void clearTestCache() {
        db.clearTestCache();
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
        private static final int D_EMPTY_SECTION = 8;

        private final String[] descriptions = new String[]{
                "estan vac\u00EDas",     //
                "contienen h1/h2",       //
                "tienen scripts",        //
                "con links sin HTTP",    //
                "tienen style tags",     //
                "tienen style attrs",    //
                "tienen <a><object></a>",//
                "tienen comentarios",    //
                "secciones vacias"
        };

        public int[] evaluate(NewsArray news, Database db) {
            int[] res = new int[descriptions.length];
            for (int i = 0; i < res.length; i++)
                res[i] = 0;

            for (News n : news) {
                Document doc = org.jsoup.Jsoup.parse(n.content);

                if (evaluateEmpty(n.content)) {
                    res[D_EMPTY]++;
                }
                if (evaluateHs(doc)) {
                    db.saveBugOn(n, "h1 or h2");
                    res[D_HS]++;
                }
                if (evaluateScripts(doc)) {
                    res[D_SCRIPTS]++;
                }
                if (evaluateLinks(n.content)) {
                    String description = "descr: ";
                    int i0 = n.content.indexOf("=\"//");
                    if (i0 == -1) i0 = n.content.indexOf("='//");
                    i0 = Math.max(0, i0 - 20);
                    int i1 = Math.min(i0 + 40, n.content.length() - 1);
                    description += n.content.substring(i0, i1);

                    db.saveBugOn(n, description);
                    res[D_LINKS]++;
                }
                if (evaluateStyleTag(doc)) {
                    db.saveBugOn(n, "style tag");
                    res[D_STYLES_TAGS]++;
                }
                if (evaluateStyleAttr(doc)) {
                    res[D_STYLE_ATTR]++;
                }
                if (evaluateAWithObject(doc)) {
                    db.saveBugOn(n, "");
                    res[D_A_OBJECT]++;
                }
                if (evaluateComments(n.content)) {
                    db.saveBugOn(n, "comments");
                    res[D_COMMENTS]++;
                }
            }

            if (news.isEmpty())
                res[D_EMPTY_SECTION]++;

            return res;
        }

        private boolean evaluateHs(Document doc) {
            return !doc.select("h1,h2").isEmpty();
        }

        private boolean evaluateEmpty(String content) {
            return content == null || content.isEmpty();
        }

        private boolean evaluateScripts(Document doc) {
            return !doc.select("script").isEmpty();
        }

        private boolean evaluateLinks(String content) {
            return content.contains("=\"//") || content.contains("='//");
        }

        private boolean evaluateStyleTag(Document doc) {
            return !doc.select("style").isEmpty();
        }

        private boolean evaluateStyleAttr(Document doc) {
            return !doc.select("[style]").isEmpty();
        }

        private boolean evaluateAWithObject(Document doc) {
            return !doc.select("a:has(img,iframe,video,figure,picture)").isEmpty();
        }

        private boolean evaluateComments(String content) {
            return content.contains("<!--");
        }

        int size() {
            return descriptions.length;
        }

        String getDescription(int position) {
            return descriptions[position];
        }

    }

}
