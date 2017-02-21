package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElJuevesSections extends Sections {

    public ElJuevesSections()
    {
        super();

        add(new Section("Principal", "http://www.eljueves.es/feeds/rss.html", 0));
        add(new Section("Las News", "http://www.eljueves.es/feeds/news.html", 0));
        add(new Section("El Temazo", "http://www.eljueves.es/feeds/temazo.html", 0));
        add(new Section("Art\u00EDculos", "http://www.eljueves.es/feeds/opinion.html", 0));
        add(new Section("Mmmh, Sexo", "http://www.eljueves.es/feeds/mmmh.html", 0));
        add(new Section("\u00A1Manda g\u00FCevos!", "http://www.eljueves.es/feeds/manda-guevos.html", 0));
        add(new Section("Blogs", "http://www.eljueves.es/feeds/blogs.html", 0));
        add(new Section("Vi\u00F1eta del d\u00EDa", "http://www.eljueves.es/feeds/vineta-del-dia.html", 0));

    }

}