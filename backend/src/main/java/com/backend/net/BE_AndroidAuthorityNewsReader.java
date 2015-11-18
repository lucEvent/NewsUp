package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_AndroidAuthorityNewsReader extends BE_NewsReader {

    public BE_AndroidAuthorityNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Lasts", "http://feed.androidauthority.com/"));
        SECTIONS.add(new BE_Section("News", "http://www.androidauthority.com/news/feed/"));

        SECTIONS.add(new BE_Section("Reviews", "http://www.androidauthority.com/reviews/feed/"));
        SECTIONS.add(new BE_Section("Apps", "http://www.androidauthority.com/apps/app-reviews/feed/"));
        SECTIONS.add(new BE_Section("Phones", "http://www.androidauthority.com/reviews/phones/feed/"));

        SECTIONS.add(new BE_Section("Apps", "http://www.androidauthority.com/apps/feed/"));
        SECTIONS.add(new BE_Section("Lists", "http://www.androidauthority.com/apps/app-lists/feed/"));
        SECTIONS.add(new BE_Section("Games", "http://www.androidauthority.com/apps/games/feed/"));

        SECTIONS.add(new BE_Section("Features", "http://www.androidauthority.com/features/feed/"));
        SECTIONS.add(new BE_Section("Opinions", "http://www.androidauthority.com/features/opinions/feed/"));
        SECTIONS.add(new BE_Section("Podcast", "http://www.androidauthority.com/podcast/feed/"));
        SECTIONS.add(new BE_Section("Tips", "http://www.androidauthority.com/tips/feed/"));
        SECTIONS.add(new BE_Section("Giveaways", "http://www.androidauthority.com/giveaways/feed/"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(content);
        doc.select(".aa_button_wrapper,.aa_see_also_block,.cbc-latest-videos").remove();

        news.content = doc.html();
        return news;
    }

}
