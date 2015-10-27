package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class AraNewsReader extends NewsReader {

    public AraNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Portada", 0, "http://www.ara.cat/rss/"));
        SECTIONS.add(new Section("El més nou", 0, "http://www.ara.cat/rss/latest/"));

        SECTIONS.add(new Section("Política", 0, "http://www.ara.cat/rss/politica/"));

        SECTIONS.add(new Section("Món", 0, "http://www.ara.cat/rss/mon/"));
        SECTIONS.add(new Section("Europa", 1, "http://www.ara.cat/rss/mon/europa/"));
        SECTIONS.add(new Section("Amèrica Llatina", 1, "http://www.ara.cat/rss/mon/america_llatina/"));
        SECTIONS.add(new Section("Estats Units", 1, "http://www.ara.cat/rss/mon/estats_units/"));
        SECTIONS.add(new Section("Pròxim Orient", 1, "http://www.ara.cat/rss/mon/orient_proxim/"));
        SECTIONS.add(new Section("Àsia", 1, "http://www.ara.cat/rss/mon/asia/"));
        SECTIONS.add(new Section("Àfrica", 1, "http://www.ara.cat/rss/mon/africa/"));

        SECTIONS.add(new Section("Societat", 0, "http://www.ara.cat/rss/societat/"));
        SECTIONS.add(new Section("Cultura", 0, "http://www.ara.cat/rss/cultura/"));
        SECTIONS.add(new Section("Economia", 0, "http://www.ara.cat/rss/economia/"));

        SECTIONS.add(new Section("Esports", 0, "http://www.ara.cat/rss/esports/"));
        SECTIONS.add(new Section("Futbol", 1, "http://www.ara.cat/rss/esports/futbol/"));
        SECTIONS.add(new Section("Barça", 1, "http://www.ara.cat/rss/esports/barca/"));
        SECTIONS.add(new Section("Espanyol", 1, "http://www.ara.cat/rss/esports/futbol/espanyol/"));
        SECTIONS.add(new Section("1a divisió", 1, "http://www.ara.cat/rss/esports/futbol/1a_divisio/"));
        SECTIONS.add(new Section("2a divisió", 1, "http://www.ara.cat/rss/esports/futbol/2a_divisio/"));
        SECTIONS.add(new Section("Futbol català", 0, "http://www.ara.cat/rss/esports/futbol/futbol_catala/"));
        SECTIONS.add(new Section("Bàsquet", 1, "http://www.ara.cat/rss/esports/basquet/"));
        SECTIONS.add(new Section("Motor", 1, "http://www.ara.cat/rss/esports/motor/"));

        SECTIONS.add(new Section("Més esports", 0, "http://www.ara.cat/rss/esports/mes_esports/"));
        SECTIONS.add(new Section("Tenis", 1, "http://www.ara.cat/rss/esports/mes_esports/tenis/"));
        SECTIONS.add(new Section("Ciclisme", 1, "http://www.ara.cat/rss/esports/mes_esports/ciclisme/"));
        SECTIONS.add(new Section("Hoquei patins", 1, "http://www.ara.cat/rss/esports/mes_esports/hoquei_patins/"));
        SECTIONS.add(new Section("Handbol", 1, "http://www.ara.cat/rss/esports/mes_esports/handbol/"));
        SECTIONS.add(new Section("Rugbi", 1, "http://www.ara.cat/rss/esports/mes_esports/rugbi/"));
        SECTIONS.add(new Section("Futbol Sala", 1, "http://www.ara.cat/rss/esports/mes_esports/futbol_sala/"));
        SECTIONS.add(new Section("Natació", 1, "http://www.ara.cat/rss/esports/mes_esports/natacio/"));

        SECTIONS.add(new Section("Xarxes", 0, "http://www.ara.cat/rss/xarxes/"));

    }

    @Override
    public News readNewsContent(News news) {
        return news;
    }
}
