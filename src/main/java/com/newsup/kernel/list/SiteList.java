package com.newsup.kernel.list;

import android.content.Context;
import android.content.res.AssetManager;

import com.newsup.R;
import com.newsup.kernel.Site;
import com.newsup.net.HelsinkiSanomatNewsReader;

import java.util.ArrayList;
import java.util.TreeSet;

public class SiteList extends ArrayList<Site> {

    private static final long serialVersionUID = 9020659010064095659L;

    public SiteList(Context context) {
        super();

        AssetManager assets = context.getAssets();

        int code = 0;
        try {
            add(new Site(-2, context.getString(R.string.mynews), 0xff9bda00, 0xff9bda00, null, null));//con 0xff778357
            add(new Site(-1, context.getString(R.string.spain), 0, Site.THEME_SPAIN, null, null));
            add(new Site(code++, "El Pais", 0xffffffff, Site.THEME_SPAIN, assets.open("elpais.png"), new com.newsup.net.ElpaisNewsReader()));
            add(new Site(code++, "20 Minutos", 0xff004594, Site.THEME_SPAIN, assets.open("20minutos.png"), new com.newsup.net._20MinutosNewsReader()));
            add(new Site(code++, "El Mundo", 0xffffffff, Site.THEME_SPAIN, assets.open("elmundo.png"), new com.newsup.net.ElMundoNewsReader()));
            add(new Site(code++, "As", 0xffba0202, Site.THEME_SPAIN, assets.open("diarioas.png"), new com.newsup.net.AsNewsReader()));
            add(new Site(code++, "Sport", 0xffeb3838, Site.THEME_SPAIN, assets.open("sport.png"), new com.newsup.net.SportNewsReader()));
            add(new Site(code++, "El Confidencial", 0xff116d9c, Site.THEME_SPAIN, assets.open("elconfidencial.png"), new com.newsup.net.ElConfidencialNewsReader()));

            add(new Site(-1, context.getString(R.string.sweden), 0xFF3333EE, Site.THEME_SWEDEN, null, null));
            add(new Site(code++, "Aftonbladet", 0xffffffff, Site.THEME_SWEDEN, assets.open("aftonbladet.png"), new com.newsup.net.AftonbladetNewsReader()));
            add(new Site(code++, "Expressen", 0xffdb2727, Site.THEME_SWEDEN, assets.open("expressen.png"), new com.newsup.net.ExpressenNewsReader()));
            add(new Site(code++, "Dagens Nyheter", 0xffeb1c2a, Site.THEME_SWEDEN, assets.open("dagensnyheter.png"), new com.newsup.net.DagensNyheterNewsReader()));
            add(new Site(code++, "Svenska Dagbladet", 0xfff5f5f5, Site.THEME_SWEDEN, assets.open("svenskadagbladet.png"), new com.newsup.net.SvDNewsReader()));
            add(new Site(code++, "Goteborgs Posten", 0xff005c9e, Site.THEME_SWEDEN, assets.open("goteborgsposten.png"), new com.newsup.net.GoteborgsPostenNewsReader()));
            add(new Site(code++, "Fria Tider", 0xffffffff, Site.THEME_SWEDEN, assets.open("friatider.png"), new com.newsup.net.FriaTiderNewsReader()));

            add(new Site(-1, context.getString(R.string.finland), 0xFF1010FF, Site.THEME_FINLAND, null, null));
            add(new Site(code++, "Helsinki times", 0xff32c8fa, Site.THEME_FINLAND, assets.open("helsinkitimes.png"), new com.newsup.net.HelsinkiTimesNewsReader()));
            add(new Site(code++, "Helsingin Sanomat", 0xff01133d, Site.THEME_FINLAND, assets.open("helsinginsanomat.png"), new HelsinkiSanomatNewsReader()));
            add(new Site(code++, "Iltalehti", 0xffff0000, Site.THEME_FINLAND, assets.open("iltalehti.png"), new com.newsup.net.IltalehtiNewsReader()));

            add(new Site(-1, context.getString(R.string.international), 0xFFDDDDDD, Site.THEME_INTERNATIONAL, null, null));
            add(new Site(code++, "Huffington Post", 0xff2c705f, Site.THEME_INTERNATIONAL, assets.open("huffingtonpost.png"), new com.newsup.net.HuffingtonPostNewsReader()));
            add(new Site(code++, "The Local", 0xfff76e05, Site.THEME_INTERNATIONAL, assets.open("thelocal.png"), new com.newsup.net.TheLocalNewsReader()));
            add(new Site(code++, "CNN", 0xffc20000, Site.THEME_INTERNATIONAL, assets.open("cnn.png"), new com.newsup.net.CNNNewsReader()));
            add(new Site(code++, "BBC", 0xffcf2127, Site.THEME_INTERNATIONAL, assets.open("bbc.png"), new com.newsup.net.BCCNewsReader()));

            add(new Site(-1, context.getString(R.string.technology), 0xFF90c3d4, Site.THEME_TECHNOLOGY, null, null));
            add(new Site(code++, "El Androide Libre", 0xffa3c23e, Site.THEME_TECHNOLOGY, assets.open("elandroidelibre.png"), new com.newsup.net.ElAndroideLibreNewsReader()));
            add(new Site(code++, "Digital Trends", 0xff0098d9, Site.THEME_TECHNOLOGY, assets.open("digitaltrends.png"), new com.newsup.net.DigitalTrendsNewsReader()));
            add(new Site(code++, "Lifehacker", 0xff94b330, Site.THEME_TECHNOLOGY, assets.open("lifehacker.png"), new com.newsup.net.LifeHackerNewsReader()));
            add(new Site(code++, "Xataka", 0xff558f22, Site.THEME_TECHNOLOGY, assets.open("xataka.png"), new com.newsup.net.XatakaNewsReader()));
            add(new Site(code++, "TED", 0xffffffff, Site.THEME_TECHNOLOGY, assets.open("ted.png"), new com.newsup.net.TEDNewsReader()));
            add(new Site(code++, "Gizmodo", 0xff9c9c9c, Site.THEME_TECHNOLOGY, assets.open("gizmodo.png"), new com.newsup.net.GizmodoNewsReader()));

            add(new Site(-1, context.getString(R.string.blogs), 0xFF90c3d4, Site.THEME_BLOGS, null, null));
            add(new Site(code++, "Medium", 0xffffffff, Site.THEME_BLOGS, assets.open("medium.png"), new com.newsup.net.MediumNewsReader()));

            add(new Site(-1, context.getString(R.string.magazines), 0xFF90c3d4, Site.THEME_MAGAZINES, null, null));
            add(new Site(code++, "Make", 0xff4ecbf5, Site.THEME_MAGAZINES, assets.open("make.png"), new com.newsup.net.MakeNewsReader()));
            add(new Site(code++, "Rolling Stone", 0xff4ecbf5, Site.THEME_MAGAZINES, assets.open("rollingstone.png"), new com.newsup.net.RollingStoneNewsReader()));
        } catch (Exception e) {
            android.util.Log.d("##SiteList##", "No se ha encontrado un logo");
            e.printStackTrace();
        }
    }

    public SiteList(TreeSet<Site> list) {
        super(list);
    }

    public int getNumSites() {
        return get(size() - 1).code + 1;
    }
}
