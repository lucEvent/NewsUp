package com.lucevent.newsup.debugbackend.kernel;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsSet;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.debugbackend.data.Database;
import com.lucevent.newsup.debugbackend.data.Task;
import com.lucevent.newsup.debugbackend.util.ReportCallback;

import java.util.ArrayList;
import java.util.TreeSet;

public class SectionsTest {

    private static final int NUM_TESTS = 5;
    private static final int AGE_THRESHOLD_MONTHS = 5;
    private static final long AGE_THRESHOLD = AGE_THRESHOLD_MONTHS * 30 * 24 * 60 * 60 * 1000L;
    private static final long MIN_NEWS_PER_SECTION = 3;

    private Database db;

    public SectionsTest()
    {
        db = new Database();
    }

    public void doTest(ReportCallback urgentCallback)
    {
        Sites sites = Sites.getDefault(true);

        Task task = db.getCurrentTask(NUM_TESTS);

        db.newRound(task);

        while (task.currentEvaluatingSite < sites.size()) {
            Site site = sites.get(task.currentEvaluatingSite);

            ArrayList<NewsSet> news = new ArrayList<>(site.getSections().size());
            for (Section section : site.getSections()) {
                if (section.url != null) {
                    news.add(new NewsSet(site.readNewsHeaders(new int[]{section.code})));
                }
            }

            int emptySectionsCounter = 0;
            int oldSectionsCounter = 0;
            int repeatedNameSectionsCounter = 0;
            int repeatedUrlSectionsCounter = 0;
            int repeatedSectionsContentCounter = 0;

            // Test 1: Empty sections
            // Test 2: Old Sections
            final long oldestAge = System.currentTimeMillis() - AGE_THRESHOLD;
            for (NewsSet ns : news) {
                // Test 1
                if (ns.size() < MIN_NEWS_PER_SECTION)
                    emptySectionsCounter++;
                // Test 2
                if (ns.size() > 0 && ns.first().date < oldestAge)
                    oldSectionsCounter++;
            }

            // Test 3: Repeated section name
            // Test 4: Repeated section links
            TreeSet<String> sectionNames = new TreeSet<>();
            TreeSet<String> sectionLinks = new TreeSet<>();
            for (Section section : site.getSections()) {
                sectionNames.add(section.name);
                if (section.url != null)
                    if (!sectionLinks.add(section.url))
                        repeatedUrlSectionsCounter++;
            }
            repeatedNameSectionsCounter = site.getSections().size() - sectionNames.size();

            // Test 4: Repeated section content
            for (int i = 0; i < news.size(); i++) {
                NewsSet ni = news.get(i);
                for (int j = i + 1; j < news.size(); j++) {
                    if (i == j) continue;

                    NewsSet nj = news.get(j);
                    if (ni.size() != nj.size()) continue;

                    int coincidences = 0;
                    for (News nwi : ni)
                        for (News nwj : nj)
                            if (nwi.link.equals(nwj.link)) {
                                coincidences++;
                                break;
                            }

                    if (coincidences == ni.size()) {
                        repeatedSectionsContentCounter++;
                        break;
                    }
                }
            }

            // Total Results
            task.siteTestResults[0] += emptySectionsCounter;
            task.siteTestResults[1] += oldSectionsCounter;
            task.siteTestResults[2] += repeatedNameSectionsCounter;
            task.siteTestResults[3] += repeatedUrlSectionsCounter;
            task.siteTestResults[4] += repeatedSectionsContentCounter;

            if (emptySectionsCounter != 0)
                db.saveBug(site, "Empty sections:" + emptySectionsCounter);

            if (oldSectionsCounter != 0)
                db.saveBug(site, "Old sections:" + oldSectionsCounter);

            if (repeatedNameSectionsCounter != 0)
                db.saveBug(site, "Repeated section names:" + repeatedNameSectionsCounter);

            if (repeatedUrlSectionsCounter != 0)
                db.saveBug(site, "Repeated section links:" + repeatedUrlSectionsCounter);

            if (repeatedSectionsContentCounter != 0)
                db.saveBug(site, "Repeated section content:" + repeatedSectionsContentCounter);

            task.currentEvaluatingSite++;
            db.save(task, false);
        }
    }

}
