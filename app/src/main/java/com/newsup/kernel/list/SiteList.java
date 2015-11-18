package com.newsup.kernel.list;

import android.content.Context;
import android.content.res.AssetManager;

import com.newsup.R;
import com.newsup.kernel.Site;
import com.newsup.kernel.section.HelsinkiSanomatSections;

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
            add(new Site(code++, "**El Pais", 0xffffffff, assets.open("elpais.png"), new com.newsup.kernel.section.ElPaisSections()));
            add(new Site(code++, "**20 Minutos", 0xff004594, assets.open("20minutos.png"), new com.newsup.kernel.section._20MinutosSections()));
            add(new Site(code++, "**El Mundo", 0xffffffff, assets.open("elmundo.png"), new com.newsup.kernel.section.ElMundoSections()));
            add(new Site(code++, "**As", 0xffba0202, assets.open("as.png"), new com.newsup.kernel.section.AsSections()));
            add(new Site(code++, "**Marca", 0xff04394a, assets.open("marca.png"), new com.newsup.kernel.section.MarcaSections()));
            add(new Site(code++, "**El Confidencial", 0xff145f85, assets.open("elconfidencial.png"), new com.newsup.kernel.section.ElConfidencialSections()));
            add(new Site(code++, "**El Diario", 0xff0061ab, assets.open("eldiario.png"), new com.newsup.kernel.section.ElDiarioSections()));
            add(new Site(code++, "**La Razón", 0xffc7c7c7, assets.open("larazon.png"), new com.newsup.kernel.section.LaRazonSections()));
            add(new Site(code++, "**Huffington Post", 0xff2c705f, assets.open("huffingtonpost.png"), new com.newsup.kernel.section.HuffingtonPostSpainSections()));

            add(new Site(-1, context.getString(R.string.catalonia), 0, null, null));
            add(new Site(code++, "**El Periódico (Cat)", 0xff0088c7, assets.open("elperiodicocat.png"), new com.newsup.kernel.section.ElPeriodicoCaSections()));
            add(new Site(code++, "**El Periódico (Esp)", 0xfff04d4d, assets.open("elperiodicoes.png"), new com.newsup.kernel.section.ElPeriodicoEsSections()));
            add(new Site(code++, "**La Vanguardia", 0xff1a4970, assets.open("lavanguardia.png"), new com.newsup.kernel.section.LaVanguardiaSections()));
            add(new Site(code++, "**Sport", 0xffd61a1a, assets.open("port.png"), new com.newsup.kernel.section.SportSections()));
            add(new Site(code++, "**Mundo Deportivo", 0xff242424, assets.open("mundodeportivo.png"), new com.newsup.kernel.section.MundoDeportivoSections()));

            add(new Site(-1, context.getString(R.string.sweden), 0, null, null));
            add(new Site(code++, "**Aftonbladet", 0xffffffff, assets.open("aftn_bdt.png"), new com.newsup.kernel.section.AftonbladetSections()));
            add(new Site(code++, "**Expressen", 0xffdb2727, assets.open("xpressen.png"), new com.newsup.kernel.section.ExpressenSections()));
            add(new Site(code++, "**Dagens Nyheter", 0xffeb1c2a, assets.open("dagensnyheter.png"), new com.newsup.kernel.section.DagensNyheterSections()));
            add(new Site(code++, "**Svenska Dagbladet", 0xfff5f5f5, assets.open("svenskadagbladet.png"), new com.newsup.kernel.section.SvenskaDagbladetSections()));
            add(new Site(code++, "**Goteborgs Posten", 0xff005c9e, assets.open("goteborgsposten.png"), new com.newsup.kernel.section.GoteborgsPostenSections()));
            add(new Site(code++, "**Fria Tider", 0xffffffff, assets.open("friatider.png"), new com.newsup.kernel.section.FriaTiderSections()));
            add(new Site(code++, "**Metro", 0xff007d3c, assets.open("metro.png"), new com.newsup.kernel.section.MetroSections()));

            add(new Site(-1, context.getString(R.string.finland), 0, null, null));
            add(new Site(code++, "**Helsinki times", 0xff32c8fa, assets.open("helsinkitimes.png"), new com.newsup.kernel.section.HelsinkiTimesSections()));
            add(new Site(code++, "**Helsingin Sanomat", 0xff01133d, assets.open("helsinginsanomat.png"), new HelsinkiSanomatSections()));
            add(new Site(code++, "**Iltalehti", 0xffff0000, assets.open("ltalehti.png"), new com.newsup.kernel.section.IltalehtiSections()));
            add(new Site(code++, "**Yle", 0xff00b4c4, assets.open("ley.png"), new com.newsup.kernel.section.YleSections()));

            add(new Site(-1, context.getString(R.string.uk), 0, null, null));
            add(new Site(code++, "**BBC", 0xffa62e30, assets.open("bbcuk.png"), new com.newsup.kernel.section.BCCSections()));

            add(new Site(-1, context.getString(R.string.us), 0, null, null));
            add(new Site(code++, "CNN", 0xffc20000, assets.open("cnnusa.png"), new com.newsup.kernel.section.CNNSections()));

            add(new Site(-1, context.getString(R.string.international), 0, null, null));
            add(new Site(code++, "**The Local", 0xfff76e05, assets.open("thelocal.png"), new com.newsup.kernel.section.TheLocalSections()));

            add(new Site(-1, context.getString(R.string.technology), 0, null, null));
            add(new Site(code++, "**El Androide Libre", 0xffa3c23e, assets.open("elandroidelibre.png"), new com.newsup.kernel.section.ElAndroideLibreSections()));
            add(new Site(code++, "**Digital Trends", 0xff0098d9, assets.open("digitaltrends.png"), new com.newsup.kernel.section.DigitalTrendsSections()));
            add(new Site(code++, "**Lifehacker", 0xff94b330, assets.open("ifehacker.png"), new com.newsup.kernel.section.LifeHackerSections()));
            add(new Site(code++, "**Xataka", 0xff558f22, assets.open("ataka.png"), new com.newsup.kernel.section.XatakaSections()));
            add(new Site(code++, "TED", 0xffffffff, assets.open("edt.png"), new com.newsup.kernel.section.TEDSections()));
            add(new Site(code++, "Gizmodo", 0xff9c9c9c, assets.open("izmodo.png"), new com.newsup.kernel.section.GizmodoSections()));
            add(new Site(code++, "**Android Authority", 0xff8cc234, assets.open("androidauthority.png"), new com.newsup.kernel.section.AndroidAuthoritySections()));

            add(new Site(-1, context.getString(R.string.blogs), 0, null, null));
            add(new Site(code++, "Medium", 0xffffffff, assets.open("edium.png"), new com.newsup.kernel.section.MediumSections()));

            add(new Site(-1, context.getString(R.string.magazines), 0, null, null));
            add(new Site(code++, "**Make", 0xff4ecbf5, assets.open("ake.png"), new com.newsup.kernel.section.MakeSections()));
            add(new Site(code++, "**Discover", 0xff171717, assets.open("discovermag.png"), new com.newsup.kernel.section.DiscoverSections()));
            add(new Site(code++, "**Rolling Stone", 0xff1c202b, assets.open("rollingstone.png"), new com.newsup.kernel.section.RollingStoneSections()));
            add(new Site(code++, "**People", 0xff20b3e8, assets.open("people.png"), new com.newsup.kernel.section.PeopleSections()));
            add(new Site(code++, "**Time", 0xffe60000, assets.open("time.png"), new com.newsup.kernel.section.TimeSections()));
            add(new Site(code++, "**The Atlantic", 0xff030202, assets.open("theatlantic.png"), new com.newsup.kernel.section.TheAtlanticSections()));
            add(new Site(code++, "**Sky and Telescope", 0xffd92326, assets.open("skyntelescope.png"), new com.newsup.kernel.section.SkyAndTelescopeSections()));
            add(new Site(code++, "**Dogster", 0xff547a94, assets.open("dogster.png"), new com.newsup.kernel.section.DogsterSections()));

        } catch (Exception e) {
            android.util.Log.d("##SiteList##", "No se ha encontrado un logo");
            e.printStackTrace();
        }
    }

    public SiteList(TreeSet<Site> list) {
        super(list);
    }

}
