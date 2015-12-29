package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_SpaceNewsNewsReader extends BE_NewsReader {

    public BE_SpaceNewsNewsReader() {
        super(true);

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Space news", "http://spacenews.com/feed/"));
        SECTIONS.add(new BE_Section("News", "http://spacenews.com/segment/news/feed/"));
        SECTIONS.add(new BE_Section("Opinion", "http://spacenews.com/segment/opinion/feed/"));
        SECTIONS.add(new BE_Section("Video", "http://spacenews.com/segment/video/feed/"));
        SECTIONS.add(new BE_Section("Launch", "http://spacenews.com/section/launch/feed/"));
        SECTIONS.add(new BE_Section("Business", "http://spacenews.com/section/business/feed/"));
        SECTIONS.add(new BE_Section("Missions", "http://spacenews.com/section/missions/feed/"));
        SECTIONS.add(new BE_Section("Policy & Politics", "http://spacenews.com/section/policy-politics/feed/"));
        SECTIONS.add(new BE_Section("People", "http://spacenews.com/section/people/feed/"));
        SECTIONS.add(new BE_Section("First-up", "http://spacenews.com/special-feature/first-up/feed/"));
        SECTIONS.add(new BE_Section("Space geeks", "http://spacenews.com/special-feature/spacegeeks/feed/"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text().replace("SpaceNews.com", "");
        if (content.length() > 0) {
            news.content = content.replace("style=", "none=");
        }
        return news;
    }

}