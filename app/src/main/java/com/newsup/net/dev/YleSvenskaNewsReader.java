package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class YleSvenskaNewsReader extends NewsReader {

    public YleSvenskaNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new SectionDeprecated("Paradsida", 0, "http://svenska.yle.fi/nyheter/rss_parad.rss"));
        SECTIONS.add(new SectionDeprecated("Inrikes", 0, "http://svenska.yle.fi/rss/inrikes"));
        SECTIONS.add(new SectionDeprecated("Utrikes", 0, "http://svenska.yle.fi/rss/utrikes"));
        SECTIONS.add(new SectionDeprecated("Ekonomi", 0, "http://svenska.yle.fi/rss/ekonomi"));
        SECTIONS.add(new SectionDeprecated("Sport", 0, "http://svenska.yle.fi/rss/sport"));
        SECTIONS.add(new SectionDeprecated("Kultur", 0, "http://svenska.yle.fi/rss/kultur-och-noje"));

        SECTIONS.add(new SectionDeprecated("Nyheter regionalt", 0, null));
        SECTIONS.add(new SectionDeprecated("Ã–sterbotten", 1, "http://svenska.yle.fi/rss/osterbotten"));
        SECTIONS.add(new SectionDeprecated("Ã…boland", 1, "http://svenska.yle.fi/rss/aboland"));
        SECTIONS.add(new SectionDeprecated("VÃ¤stnyland", 1, "http://svenska.yle.fi/rss/vastnyland"));
        SECTIONS.add(new SectionDeprecated("Huvudstadsregionen", 1, "http://svenska.yle.fi/rss/huvudstadsregionen"));
        SECTIONS.add(new SectionDeprecated("Ã–stnyland", 1, "http://svenska.yle.fi/rss/ostnyland"));

        SECTIONS.add(new SectionDeprecated("Mat & fritid", 0, "http://svenska.yle.fi/rss/mat-och-fritid"));

    }

}