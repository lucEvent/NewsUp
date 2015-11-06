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

    public SiteList() {
    }

    public SiteList(Context context) {
        super();

        AssetManager assets = context.getAssets();

        int code = 0;
        try {
            add(new Site(-1, context.getString(R.string.spain), 0, null, null));
            add(new Site(code++, "**El Pais", 0xffffffff, assets.open("elpais.png"), new com.newsup.net.ElpaisNewsReader()));
            add(new Site(code++, "**20 Minutos", 0xff004594, assets.open("20minutos.png"), new com.newsup.net._20MinutosNewsReader()));
            add(new Site(code++, "**El Mundo", 0xffffffff, assets.open("elmundo.png"), new com.newsup.net.ElMundoNewsReader()));
            add(new Site(code++, "**As", 0xffba0202, assets.open("as.png"), new com.newsup.net.AsNewsReader()));
            add(new Site(code++, "**Marca", 0xff04394a, assets.open("marca.png"), new com.newsup.net.MarcaNewsReader()));
            add(new Site(code++, "**El Confidencial", 0xff145f85, assets.open("elconfidencial.png"), new com.newsup.net.ElConfidencialNewsReader()));
            add(new Site(code++, "**El Diario", 0xff0061ab, assets.open("eldiario.png"), new com.newsup.net.ElDiarioNewsReader()));
            add(new Site(code++, "**La Razón", 0xffc7c7c7, assets.open("larazon.png"), new com.newsup.net.LaRazonNewsReader()));
            add(new Site(code++, "**Huffington Post", 0xff2c705f, assets.open("huffingtonpost.png"), new com.newsup.net.HuffingtonPostSpainNewsReader()));

            add(new Site(-1, context.getString(R.string.catalonia), 0, null, null));
            add(new Site(code++, "**El Periódico (Cat)", 0xff0088c7, assets.open("elperiodicocat.png"), new com.newsup.net.ElPeriodicoCaNewsReader()));
            add(new Site(code++, "**El Periódico (Esp)", 0xfff04d4d, assets.open("elperiodicoes.png"), new com.newsup.net.ElPeriodicoEsNewsReader()));
            add(new Site(code++, "**La Vanguardia", 0xff1a4970, assets.open("lavanguardia.png"), new com.newsup.net.LaVanguardiaNewsReader()));
            add(new Site(code++, "**Sport", 0xffd61a1a, assets.open("sport.png"), new com.newsup.net.SportNewsReader()));
            add(new Site(code++, "**Mundo Deportivo", 0xff242424, assets.open("mundodeportivo.png"), new com.newsup.net.MundoDeportivoNewsReader()));

            add(new Site(-1, context.getString(R.string.sweden), 0, null, null));
            add(new Site(code++, "**Aftonbladet", 0xffffffff, assets.open("aftonbladet.png"), new com.newsup.net.AftonbladetNewsReader()));
            add(new Site(code++, "**Expressen", 0xffdb2727, assets.open("expressen.png"), new com.newsup.net.ExpressenNewsReader()));
            add(new Site(code++, "**Dagens Nyheter", 0xffeb1c2a, assets.open("dagensnyheter.png"), new com.newsup.net.DagensNyheterNewsReader()));
            add(new Site(code++, "**Svenska Dagbladet", 0xfff5f5f5, assets.open("svenskadagbladet.png"), new com.newsup.net.SvenskaDagbladetNewsReader()));
            add(new Site(code++, "**Goteborgs Posten", 0xff005c9e, assets.open("goteborgsposten.png"), new com.newsup.net.GoteborgsPostenNewsReader()));
            add(new Site(code++, "**Fria Tider", 0xffffffff, assets.open("friatider.png"), new com.newsup.net.FriaTiderNewsReader()));
            add(new Site(code++, "Metro", 0xff007d3c, assets.open("metro.png"), new com.newsup.net.MetroNewsReader()));

            add(new Site(-1, context.getString(R.string.finland), 0, null, null));
            add(new Site(code++, "Helsinki times", 0xff32c8fa, assets.open("helsinkitimes.png"), new com.newsup.net.HelsinkiTimesNewsReader()));
            add(new Site(code++, "Helsingin Sanomat", 0xff01133d, assets.open("helsinginsanomat.png"), new HelsinkiSanomatNewsReader()));
            add(new Site(code++, "Iltalehti", 0xffff0000, assets.open("iltalehti.png"), new com.newsup.net.IltalehtiNewsReader()));
            add(new Site(code++, "**Yle", 0xff00b4c4, assets.open("yle.png"), new com.newsup.net.YleNewsReader()));

            add(new Site(-1, context.getString(R.string.uk), 0, null, null));
            add(new Site(code++, "BBC", 0xffa62e30, assets.open("bbc.png"), new com.newsup.net.BCCNewsReader()));

            add(new Site(-1, context.getString(R.string.us), 0, null, null));
            add(new Site(code++, "CNN", 0xffc20000, assets.open("cnn.png"), new com.newsup.net.CNNNewsReader()));

            add(new Site(-1, context.getString(R.string.international), 0, null, null));
            add(new Site(code++, "**The Local", 0xfff76e05, assets.open("thelocal.png"), new com.newsup.net.TheLocalNewsReader()));

            add(new Site(-1, context.getString(R.string.technology), 0, null, null));
            add(new Site(code++, "**El Androide Libre", 0xffa3c23e, assets.open("elandroidelibre.png"), new com.newsup.net.ElAndroideLibreNewsReader()));
            add(new Site(code++, "Digital Trends", 0xff0098d9, assets.open("digitaltrends.png"), new com.newsup.net.DigitalTrendsNewsReader()));
            add(new Site(code++, "**Lifehacker", 0xff94b330, assets.open("lifehacker.png"), new com.newsup.net.LifeHackerNewsReader()));
            add(new Site(code++, "**Xataka", 0xff558f22, assets.open("xataka.png"), new com.newsup.net.XatakaNewsReader()));
            add(new Site(code++, "TED", 0xffffffff, assets.open("ted.png"), new com.newsup.net.TEDNewsReader()));
            add(new Site(code++, "Gizmodo", 0xff9c9c9c, assets.open("gizmodo.png"), new com.newsup.net.GizmodoNewsReader()));
            add(new Site(code++, "**Android Authority", 0xff8cc234, assets.open("androidauthority.png"), new com.newsup.net.AndroidAuthorityNewsReader()));

            add(new Site(-1, context.getString(R.string.blogs), 0, null, null));
            add(new Site(code++, "Medium", 0xffffffff, assets.open("medium.png"), new com.newsup.net.MediumNewsReader()));

            add(new Site(-1, context.getString(R.string.magazines), 0, null, null));
            add(new Site(code++, "Make", 0xff4ecbf5, assets.open("make.png"), new com.newsup.net.MakeNewsReader()));
            add(new Site(code++, "Discover", 0xff171717, assets.open("discovermag.png"), new com.newsup.net.DiscoverNewsReader()));
            add(new Site(code++, "Rolling Stone", 0xff1c202b, assets.open("rollingstone.png"), new com.newsup.net.RollingStoneNewsReader()));
            add(new Site(code++, "People", 0xff20b3e8, assets.open("people.png"), new com.newsup.net.PeopleNewsReader()));
            add(new Site(code++, "Time", 0xffe60000, assets.open("time.png"), new com.newsup.net.TimeNewsReader()));
            add(new Site(code++, "**The Atlantic", 0xff030202, assets.open("theatlantic.png"), new com.newsup.net.TheAtlanticNewsReader()));
            add(new Site(code++, "**Sky and Telescope", 0xffd92326, assets.open("skyntelescope.png"), new com.newsup.net.SkyAndTelescopeNewsReader()));
            add(new Site(code++, "**Dogster", 0xff547a94, assets.open("dogster.png"), new com.newsup.net.DogsterNewsReader()));

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
