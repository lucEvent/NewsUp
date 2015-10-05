package com.newsup.kernel.list;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.Site;
import com.newsup.net.AsNewsReader;
import com.newsup.net.DigitalTrendsNewsReader;
import com.newsup.net.ElAndroideLibreNewsReader;
import com.newsup.net.ElMundoNewsReader;
import com.newsup.net.ElpaisNewsReader;
import com.newsup.net.HSNewsReader;
import com.newsup.net.HuffingtonPostNewsReader;
import com.newsup.net.IltalehtiNewsReader;
import com.newsup.net.LifeHackerNewsReader;
import com.newsup.net.SportNewsReader;
import com.newsup.net.SvDNewsReader;
import com.newsup.net.TheLocalNewsReader;
import com.newsup.net.XatakaNewsReader;
import com.newsup.net._20MinutosNewsReader;

import java.util.ArrayList;

public class SiteList extends ArrayList<Site> {

    private static final long serialVersionUID = 9020659010064095659L;

    public SiteList(Handler handler, Context context) {
        super();

        add(new Site(0, "El Pais", new ElpaisNewsReader(handler, context)));
        add(new Site(1, "As", new AsNewsReader(handler, context)));
        add(new Site(2, "20 Minutos", new _20MinutosNewsReader(handler, context)));
        add(new Site(3, "Sport", new SportNewsReader(handler, context)));
        add(new Site(4, "Helsingin Sanomat", new HSNewsReader(handler, context)));
        add(new Site(5, "Svenska Dagbladet", new SvDNewsReader(handler, context)));
        add(new Site(6, "El Mundo", new ElMundoNewsReader(handler, context)));
        add(new Site(7, "Huffington Post", new HuffingtonPostNewsReader(handler, context)));
        add(new Site(8, "Iltalehti", new IltalehtiNewsReader(handler, context)));
        add(new Site(9, "Digital Trends", new DigitalTrendsNewsReader(handler, context)));
        add(new Site(10, "The Local", new TheLocalNewsReader(handler, context)));
        add(new Site(11, "Lifehacker", new LifeHackerNewsReader(handler, context)));
        add(new Site(12, "Xataka", new XatakaNewsReader(handler, context)));
        add(new Site(13, "El Androide Libre", new ElAndroideLibreNewsReader(handler, context)));

    }


}
