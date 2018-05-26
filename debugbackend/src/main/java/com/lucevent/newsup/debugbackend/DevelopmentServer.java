package com.lucevent.newsup.debugbackend;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.Enclosures;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.NewsSet;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.SiteCategory;
import com.lucevent.newsup.data.util.SiteCountry;
import com.lucevent.newsup.data.util.SiteLanguage;
import com.lucevent.newsup.data.util.Tags;
import com.lucevent.newsup.debugbackend.data.Database;
import com.lucevent.newsup.debugbackend.data.Error;
import com.lucevent.newsup.debugbackend.data.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DevelopmentServer extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        processPetition(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        processPetition(req, resp);
    }

    private void processPetition(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");

        //
        /*
         ** secciones vacias **
/*
        print(resp, "starting test");
        Site s = new Site(540, "Daily Mail", 0xff004db3, "http://www.dailymail.co.uk",
                SiteCountry.UK | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.DailyMailSections.class, com.lucevent.newsup.data.reader.DailyMail.class);
        doTest(s, resp);
        print(resp, "test finished");
*/
        //

        if (req.getParameter("error") != null) {

            StringBuilder sb = new StringBuilder();
            sb.append("<content>");

            ArrayList<Error> errors = db.getErrors();
            for (Error e : errors)
                sb.append("<error sc='").append(e.n_site)
                        .append("' sn='").append(sites.getSiteByCode(e.n_site).name)
                        .append("'><link>").append(e.n_link)
                        .append("</link><content>").append(e.n_content)
                        .append("</content></error>");

            sb.append("</content>");
            print(resp, sb);

        } else if (req.getParameter("tasks") != null) {

            StringBuilder sb = new StringBuilder();
            sb.append("<content>");

            TreeSet<Task> tasks = db.getTasks();
            for (Task task : tasks) {
                sb.append("<task current='").append(task.currentEvaluatingSite)
                        .append("' start='").append(task.startTime)
                        .append("' end='").append(task.finishTime)
                        .append("' rounds='").append(task.rounds)
                        .append("' num='").append(task.totalNumNews)
                        .append("' results='").append(Arrays.toString(task.totalTestResults))
                        .append("'/>");
            }
            sb.append("</content>");
            print(resp, sb);

        }

    }

    static private Sites sites;
    static private Database db;

    @Override
    public void init() throws ServletException
    {
        super.init();

        sites = Sites.getDefault(true);
        db = new Database();
    }

    private static final int NUM_TESTS = 4;
    private static final int AGE_THRESHOLD_MONTHS = 5;
    private static final long AGE_THRESHOLD = (AGE_THRESHOLD_MONTHS + 1) * 30 * 24 * 60 * 60 * 1000L;
    private static final long MIN_NEWS_PER_SECTION = 1;

    private void doTest(Site site, HttpServletResponse resp) throws IOException
    {
        Sections sections = site.getSections();
        NewsReader reader = new NewsReader(resp);
        ArrayList<String> secNames = new ArrayList<>(site.getSections().size());
        ArrayList<NewsSet> news = new ArrayList<>(site.getSections().size());
        for (int i = 0; i < sections.size(); i++) {
            Section section = sections.get(i);
            System.out.print(".");
            if (section.url != null) {
                secNames.add(section.name);
                news.add(new NewsSet(reader.readNewsHeaders(site.code, new int[]{section.code})));
            }
        }
        print(resp, "\n\n ### Results ###");

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
            if (ns.size() < MIN_NEWS_PER_SECTION) {
                emptySectionsCounter++;
            }
            // Test 2
            if (ns.size() > 0 && ns.first().date < oldestAge) {
                oldSectionsCounter++;
            }
        }
        print(resp, "Empty sections: " + emptySectionsCounter);
        print(resp, "Old sections: " + oldSectionsCounter);

        // Test 3: Repeated section name
        // Test 4: Repeated section links
        TreeSet<String> sectionNames = new TreeSet<>();
        TreeSet<String> sectionLinks = new TreeSet<>();
        for (Section section : site.getSections()) {
            if (!sectionNames.add(section.name)) {
                print(resp, "NaRep: " + section.name + " [" + section.url + "]");
            }
        }
        for (Section section : site.getSections()) {
            if (section.url != null) {
                if (!sectionLinks.add(section.url)) {
                    repeatedUrlSectionsCounter++;
                    print(resp, "LiRep: " + section.name + " [" + section.url + "]");
                }
            }
        }
        repeatedNameSectionsCounter = site.getSections().size() - sectionNames.size();

        print(resp, "Repeated section names: " + repeatedNameSectionsCounter);
        print(resp, "Repeated section links: " + repeatedUrlSectionsCounter);

        // Test 5: Repeated section content
        for (int i = 0; i < news.size(); i++) {
            NewsSet ni = news.get(i);
            for (int j = i + 1; j < news.size(); j++) {

                NewsSet nj = news.get(j);
                if (ni.size() != nj.size()) {
                    continue;
                }

                int coincidences = 0;
                for (News nwi : ni) {
                    for (News nwj : nj) {
                        if (nwi.link.equals(nwj.link)) {
                            coincidences++;
                            break;
                        }
                    }
                }

                if (coincidences == ni.size()) {
                    print(resp, "Coinciden: " + secNames.get(i) + " y " + secNames.get(j));
                    repeatedSectionsContentCounter++;
                    break;
                }
            }
        }
        print(resp, "Repeated section content: " + repeatedSectionsContentCounter);
        print(resp, "");
        print(resp, "[out of " + sections.size() + " sections]");

    }

    ////////

    public final class NewsReader {

        private static final int HASH_TITLE = 110371416;
        private static final int HASH_LINK = 3321850;
        private static final int HASH_DATE = 3076014;
        private static final int HASH_DESCRIPTION = -1724546052;
        private static final int HASH_CATEGORIES = 1296516636;
        private static final int HASH_CONTENT = 951530617;
        private static final int HASH_ENCLOSURE = 1432853874;
        private static final int HASH_SECTION = 1970241253;
        private static final int HASH_ERROR = 96784904;

        private static final String query_index = "http://newsup-5.appspot.com/dev?debugsite&site=%s%s";
        private HttpServletResponse resp;

        public NewsReader(HttpServletResponse resp)
        {
            this.resp = resp;
        }

        public final NewsArray readNewsHeaders(int site_code, int[] section_codes) throws IOException
        {
            return readHeaders(String.format(query_index, site_code, stringify(section_codes)), site_code);
        }

        private String stringify(int[] section_codes)
        {
            StringBuilder sectArray = new StringBuilder(section_codes.length * 3);
            for (int section_code : section_codes)
                if (section_code != -1)
                    sectArray.append(',').append(section_code);

            return sectArray.toString();
        }

        private final NewsArray readHeaders(String query_link, int site_code) throws IOException
        {
            print(resp, "[" + site_code + "] Query: " + query_link);

            org.jsoup.nodes.Document doc = getDocument(query_link);
            if (doc == null) return new NewsArray();

            NewsArray res = new NewsArray();

            for (org.jsoup.nodes.Element item : doc.select("item")) {
                String title = "", link = "", description = "", content = "", categories = "";
                int section = 0;
                long date = 0;
                Enclosures enclosures = new Enclosures();

                for (org.jsoup.nodes.Element prop : item.children()) {

                    switch (prop.tagName().hashCode()) {
                        case HASH_ERROR:
                            print(resp, "Returned error: " + prop.html());
                            break;
                        case HASH_TITLE:
                            title = prop.html();
                            break;
                        case HASH_LINK:
                            link = prop.html();
                            break;
                        case HASH_DATE:
                            date = Long.parseLong(prop.html());
                            break;
                        case HASH_DESCRIPTION:
                            description = prop.text();
                            break;
                        case HASH_CATEGORIES:
                            categories = prop.html();
                            break;
                        case HASH_CONTENT:
                            content = prop.html();
                            break;
                        case HASH_ENCLOSURE:
                            enclosures.add(new Enclosure(prop.text(), "image", ""));
                            break;
                        case HASH_SECTION:
                            section = Integer.parseInt(prop.text());
                            break;
                    }
                }
                if (!title.isEmpty()) {
                    News news = new News(title, link, description, date, new Tags(categories), site_code, section, 0);
                    news.enclosures = enclosures;
                    news.content = content;

                    res.add(news);
                }
            }
            print(resp, "[" + site_code + "] -> read " + res.size() + " news");
            return res;
        }

        private org.jsoup.nodes.Document getDocument(String url) throws IOException
        {
            try {
                return org.jsoup.Jsoup.connect(url).parser(org.jsoup.parser.Parser.xmlParser()).timeout(10000).get();
            } catch (Exception e) {
                print(resp, "[NR] Can't read url: " + url);
            }
            return null;
        }

    }

    void print(HttpServletResponse r, CharSequence msg) throws IOException
    {
        r.getWriter().println(msg);
        r.flushBuffer();
    }

}
