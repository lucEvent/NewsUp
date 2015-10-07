package com.newsup.kernel.list;

import android.content.Context;
import android.os.Handler;

import com.newsup.R;
import com.newsup.kernel.Site;

import java.util.ArrayList;

public class SiteList extends ArrayList<Site> {

    private static final long serialVersionUID = 9020659010064095659L;

    public SiteList(Handler handler, Context context) {
        super();

        int code = 0;
        add(new Site(-1, context.getString(R.string.spain), 0, Site.THEME_SPAIN, null));
        add(new Site(code++, "El Pais", 0xffffffff, Site.THEME_SPAIN, new com.newsup.net.ElpaisNewsReader(handler, context)));
        add(new Site(code++, "20 Minutos", 0xff004594, Site.THEME_SPAIN, new com.newsup.net._20MinutosNewsReader(handler, context)));
        add(new Site(code++, "El Mundo", 0xffffffff, Site.THEME_SPAIN, new com.newsup.net.ElMundoNewsReader(handler, context)));
        add(new Site(code++, "As", 0xffba0202, Site.THEME_SPAIN, new com.newsup.net.AsNewsReader(handler, context)));
        add(new Site(code++, "Sport", 0xffeb3838, Site.THEME_SPAIN, new com.newsup.net.SportNewsReader(handler, context)));

        add(new Site(-1, context.getString(R.string.sweden), 0xFF3333EE, Site.THEME_SWEDEN, null));
        add(new Site(code++, "Svenska Dagbladet", 0xfff5f5f5, Site.THEME_SWEDEN, new com.newsup.net.SvDNewsReader(handler, context)));

        add(new Site(-1, context.getString(R.string.finland), 0xFF1010FF, Site.THEME_FINLAND, null));
        add(new Site(code++, "Helsinki times", 0xff32c8fa, Site.THEME_FINLAND, new com.newsup.net.HelsinkiTimesNewsReader(handler, context)));
        add(new Site(code++, "Helsingin Sanomat", 0xff01133d, Site.THEME_FINLAND, new com.newsup.net.HSNewsReader(handler, context)));
        add(new Site(code++, "Iltalehti", 0xffff0000, Site.THEME_FINLAND, new com.newsup.net.IltalehtiNewsReader(handler, context)));

        add(new Site(-1, context.getString(R.string.international), 0xFFDDDDDD, Site.THEME_INTERNATIONAL, null));
        add(new Site(code++, "Huffington Post", 0xff2c705f, Site.THEME_INTERNATIONAL, new com.newsup.net.HuffingtonPostNewsReader(handler, context)));
        add(new Site(code++, "The Local", 0xfff76e05, Site.THEME_INTERNATIONAL, new com.newsup.net.TheLocalNewsReader(handler, context)));
        add(new Site(code++, "CNN", 0xffc20000, Site.THEME_INTERNATIONAL, new com.newsup.net.CNNNewsReader(handler, context)));
        add(new Site(code++, "BBC", 0xffcf2127, Site.THEME_INTERNATIONAL, new com.newsup.net.BCCNewsReader(handler, context)));

        add(new Site(-1, context.getString(R.string.technology), 0xFF90c3d4, Site.THEME_TECHNOLOGY, null));
        add(new Site(code++, "El Androide Libre", 0xffa3c23e, Site.THEME_TECHNOLOGY, new com.newsup.net.ElAndroideLibreNewsReader(handler, context)));
        add(new Site(code++, "Digital Trends", 0xff0098d9, Site.THEME_TECHNOLOGY, new com.newsup.net.DigitalTrendsNewsReader(handler, context)));
        add(new Site(code++, "Lifehacker", 0xff94b330, Site.THEME_TECHNOLOGY, new com.newsup.net.LifeHackerNewsReader(handler, context)));
        add(new Site(code++, "Xataka", 0xff558f22, Site.THEME_TECHNOLOGY, new com.newsup.net.XatakaNewsReader(handler, context)));
        add(new Site(code++, "TED", 0xffffffff, Site.THEME_TECHNOLOGY, new com.newsup.net.TEDNewsReader(handler, context)));

    }

    public int getNumSites() {
        return get(size() - 1).code + 1;
    }
}
