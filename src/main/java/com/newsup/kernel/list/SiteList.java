package com.newsup.kernel.list;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.Site;

import java.util.ArrayList;

public class SiteList extends ArrayList<Site> {

    private static final long serialVersionUID = 9020659010064095659L;

    public SiteList(Handler handler, Context context) {
        super();

        add(new Site(0, "El Pais",0xffffffff, new com.newsup.net.ElpaisNewsReader(handler, context)));
        add(new Site(1, "As", 0xffba0202, new com.newsup.net.AsNewsReader(handler, context)));
        add(new Site(2, "20 Minutos", 0xff004594, new com.newsup.net._20MinutosNewsReader(handler, context)));
        add(new Site(3, "Sport",0xffeb3838, new com.newsup.net.SportNewsReader(handler, context)));
        add(new Site(4, "Helsingin Sanomat",0xff01133d, new com.newsup.net.HSNewsReader(handler, context)));
        add(new Site(5, "Svenska Dagbladet",0xfff5f5f5, new com.newsup.net.SvDNewsReader(handler, context)));
        add(new Site(6, "El Mundo", 0xffffffff,new com.newsup.net.ElMundoNewsReader(handler, context)));
        add(new Site(7, "Huffington Post",0xff2c705f, new com.newsup.net.HuffingtonPostNewsReader(handler, context)));
        add(new Site(8, "Iltalehti",0xffff0000, new com.newsup.net.IltalehtiNewsReader(handler, context)));
        add(new Site(9, "Digital Trends",0xff0098d9, new com.newsup.net.DigitalTrendsNewsReader(handler, context)));
        add(new Site(10, "The Local", 0xfff76e05,new com.newsup.net.TheLocalNewsReader(handler, context)));
        add(new Site(11, "Lifehacker",0xff94b330, new com.newsup.net.LifeHackerNewsReader(handler, context)));
        add(new Site(12, "Xataka",0xff558f22, new com.newsup.net.XatakaNewsReader(handler, context)));
        add(new Site(13, "El Androide Libre",0xffa3c23e, new com.newsup.net.ElAndroideLibreNewsReader(handler, context)));
        add(new Site(14, "Helsinki times",0xff32c8fa, new com.newsup.net.HelsinkiTimesNewsReader(handler, context)));
        add(new Site(15, "TED",0xffffffff, new com.newsup.net.TEDNewsReader(handler, context)));
        add(new Site(16, "CNN", 0xffc20000, new com.newsup.net.CNNNewsReader(handler, context)));
        add(new Site(17, "BBC", 0xffcf2127,new com.newsup.net.BCCNewsReader(handler, context)));

    }


}
