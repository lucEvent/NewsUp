package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class YleSvenskaNewsReader extends NewsReader {

    public YleSvenskaNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Paradsida", 0, "http://svenska.yle.fi/nyheter/rss_parad.rss"));
        SECTIONS.add(new Section("Inrikes", 0, "http://svenska.yle.fi/rss/inrikes"));
        SECTIONS.add(new Section("Utrikes", 0, "http://svenska.yle.fi/rss/utrikes"));
        SECTIONS.add(new Section("Ekonomi", 0, "http://svenska.yle.fi/rss/ekonomi"));
        SECTIONS.add(new Section("Sport", 0, "http://svenska.yle.fi/rss/sport"));
        SECTIONS.add(new Section("Kultur", 0, "http://svenska.yle.fi/rss/kultur-och-noje"));

        SECTIONS.add(new Section("Nyheter regionalt", 0, null));
        SECTIONS.add(new Section("Ã–sterbotten", 1, "http://svenska.yle.fi/rss/osterbotten"));
        SECTIONS.add(new Section("Ã…boland", 1, "http://svenska.yle.fi/rss/aboland"));
        SECTIONS.add(new Section("VÃ¤stnyland", 1, "http://svenska.yle.fi/rss/vastnyland"));
        SECTIONS.add(new Section("Huvudstadsregionen", 1, "http://svenska.yle.fi/rss/huvudstadsregionen"));
        SECTIONS.add(new Section("Ã–stnyland", 1, "http://svenska.yle.fi/rss/ostnyland"));

        SECTIONS.add(new Section("Mat & fritid", 0, "http://svenska.yle.fi/rss/mat-och-fritid"));

    }

}