package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class SpaceNewsNewsReader extends NewsReader {

    public SpaceNewsNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new SectionDeprecated("Space news", 0, "http://spacenews.com/feed/"));
        SECTIONS.add(new SectionDeprecated("News", 0, "http://spacenews.com/segment/news/feed/"));
        SECTIONS.add(new SectionDeprecated("Opinion", 0, "http://spacenews.com/segment/opinion/feed/"));
        SECTIONS.add(new SectionDeprecated("Video", 0, "http://spacenews.com/segment/video/feed/"));

        SECTIONS.add(new SectionDeprecated("Launch", 0, "http://spacenews.com/section/launch/feed/"));
        SECTIONS.add(new SectionDeprecated("Business", 0, "http://spacenews.com/section/business/feed/"));
        SECTIONS.add(new SectionDeprecated("Missions", 0, "http://spacenews.com/section/missions/feed/"));
        SECTIONS.add(new SectionDeprecated("Policy & Politics", 0, "http://spacenews.com/section/policy-politics/feed/"));
        SECTIONS.add(new SectionDeprecated("People", 0, "http://spacenews.com/section/people/feed/"));
        SECTIONS.add(new SectionDeprecated("First-up", 0, "http://spacenews.com/special-feature/first-up/feed/"));
        SECTIONS.add(new SectionDeprecated("Space geeks", 0, "http://spacenews.com/special-feature/spacegeeks/feed/"));

    }

}