package com.newsup.kernel.set;

import android.content.Context;
import android.content.res.AssetManager;

import com.newsup.R;
import com.newsup.kernel.basic.Site;

import java.util.ArrayList;
import java.util.TreeSet;

public class SiteList extends ArrayList<Site> {

    private static final long serialVersionUID = 9020659010064095659L;

    public SiteList() {
    }

    public SiteList(Context context) {
        super();

        AssetManager assets = context.getAssets();

        try {
            add(new Site(-1, context.getString(R.string.spain), 0, null, null));
            add(new Site(100, "El Pais", 0xffffffff, assets.open("elpais.png"), new com.newsup.kernel.section.ElPaisSections()));
            add(new Site(105, "20 Minutos", 0xff004594, assets.open("20minutos.png"), new com.newsup.kernel.section._20MinutosSections()));
            add(new Site(110, "El Mundo", 0xffffffff, assets.open("elmundo.png"), new com.newsup.kernel.section.ElMundoSections()));
            add(new Site(115, "As", 0xffba0202, assets.open("as.png"), new com.newsup.kernel.section.AsSections()));
            add(new Site(120, "Marca", 0xff04394a, assets.open("marca.png"), new com.newsup.kernel.section.MarcaSections()));
            add(new Site(125, "El Confidencial", 0xff145f85, assets.open("elconfidencial.png"), new com.newsup.kernel.section.ElConfidencialSections()));
            add(new Site(130, "El Diario", 0xff0061ab, assets.open("eldiario.png"), new com.newsup.kernel.section.ElDiarioSections()));
            add(new Site(135, "La Razón", 0xffc7c7c7, assets.open("larazon.png"), new com.newsup.kernel.section.LaRazonSections()));
            add(new Site(140, "Huffington Post", 0xff2c705f, assets.open("huffingtonpost.png"), new com.newsup.kernel.section.HuffingtonPostSpainSections()));
            add(new Site(145, "Europa press", 0xffffffff, assets.open("europapress.png"), new com.newsup.kernel.section.EuropaPressSections()));
            add(new Site(150, "Diario Córdoba", 0xffde2632, assets.open("diariocordoba.png"), new com.newsup.kernel.section.DiarioCordobaSections()));

            add(new Site(-1, context.getString(R.string.catalonia), 0, null, null));
            add(new Site(200, "El Periódico (Cat)", 0xff0088c7, assets.open("elperiodicocat.png"), new com.newsup.kernel.section.ElPeriodicoCaSections()));
            add(new Site(205, "El Periódico (Esp)", 0xfff04d4d, assets.open("elperiodicoes.png"), new com.newsup.kernel.section.ElPeriodicoEsSections()));
            add(new Site(210, "La Vanguardia", 0xff1a4970, assets.open("lavanguardia.png"), new com.newsup.kernel.section.LaVanguardiaSections()));
            add(new Site(215, "Sport", 0xffd61a1a, assets.open("sport.png"), new com.newsup.kernel.section.SportSections()));
            add(new Site(220, "Mundo Deportivo", 0xff242424, assets.open("mundodeportivo.png"), new com.newsup.kernel.section.MundoDeportivoSections()));

            add(new Site(-1, context.getString(R.string.sweden), 0, null, null));
            add(new Site(300, "Aftonbladet", 0xffffffff, assets.open("aftonbladet.png"), new com.newsup.kernel.section.AftonbladetSections()));
            add(new Site(305, "Expressen", 0xffdb2727, assets.open("expressen.png"), new com.newsup.kernel.section.ExpressenSections()));
            add(new Site(310, "Dagens Nyheter", 0xffeb1c2a, assets.open("dagensnyheter.png"), new com.newsup.kernel.section.DagensNyheterSections()));
            add(new Site(315, "Svenska Dagbladet", 0xfff5f5f5, assets.open("svenskadagbladet.png"), new com.newsup.kernel.section.SvenskaDagbladetSections()));
            add(new Site(320, "Goteborgs Posten", 0xff005c9e, assets.open("goteborgsposten.png"), new com.newsup.kernel.section.GoteborgsPostenSections()));
            add(new Site(325, "Fria Tider", 0xffffffff, assets.open("friatider.png"), new com.newsup.kernel.section.FriaTiderSections()));
            add(new Site(330, "Metro", 0xff007d3c, assets.open("metro.png"), new com.newsup.kernel.section.MetroSections()));

/*            add(new Site(-1, context.getString(R.string.finland), 0, null, null));
            add(new Site(400, "Helsinki times", 0xff32c8fa, assets.open("helsinkitimes.png"), new com.newsup.kernel.section.HelsinkiTimesSections()));
            add(new Site(405, "Helsingin Sanomat", 0xff01133d, assets.open("helsinginsanomat.png"), new HelsinkiSanomatSections()));
            add(new Site(410, "Iltalehti", 0xffff0000, assets.open("iltalehti.png"), new com.newsup.kernel.section.IltalehtiSections()));
            add(new Site(415, "Yle", 0xff00b4c4, assets.open("yle.png"), new com.newsup.kernel.section.YleSections()));
//          add(new Site(420, "Yle Svenska", 0xff, assets.open("ylesvenska.png"), new com.newsup.kernel.section.YleSvenskaSections()));
//          add(new Site(425, "Ilta Sanomat", 0xff, assets.open("iltasanomat.png"), new com.newsup.kernel.section.IltaSanomatSections()));
*/
            add(new Site(-1, context.getString(R.string.uk), 0, null, null));
            add(new Site(500, "BBC", 0xffa62e30, assets.open("bbc.png"), new com.newsup.kernel.section.BCCSections()));
//          add(new Site(505, "The Telegraph", 0xff, assets.open("thetelegraph.png"), new com.newsup.kernel.section.TheTelegraphSections()));
//          add(new Site(510, "The Huffington Post", 0xff, assets.open("huffingtonpost.png"), new com.newsup.kernel.section.HuffingtonPostUKSections()));

            add(new Site(-1, context.getString(R.string.us), 0, null, null));
            add(new Site(600, "CNN", 0xffc20000, assets.open("cnn.png"), new com.newsup.kernel.section.CNNSections()));
//          add(new Site(605, "The Huffington Post", 0xff, assets.open("huffingtonpost.png"), new com.newsup.kernel.section.HuffingtonPostUSASections()));

            add(new Site(-1, context.getString(R.string.international), 0, null, null));
            add(new Site(700, "The Local", 0xfff76e05, assets.open("thelocal.png"), new com.newsup.kernel.section.TheLocalSections()));
//          add(new Site(705, "The Huffington Post (Arabic)", 0xff, assets.open("huffingtonpost.png"), new com.newsup.kernel.section.HuffingtonPostArabicSections()));
//          add(new Site(710, "The Huffington Post (Australia)", 0xff, assets.open("huffingtonpost.png"), new com.newsup.kernel.section.HuffingtonPostAustraliaSections()));
//          add(new Site(715, "The Huffington Post (Brasil)", 0xff, assets.open("huffingtonpost.png"), new com.newsup.kernel.section.HuffingtonPostBrasilSections()));
//          add(new Site(720, "The Huffington Post (Canada)", 0xff, assets.open("huffingtonpost.png"), new com.newsup.kernel.section.HuffingtonPostCanadaSections()));

            add(new Site(-1, context.getString(R.string.technology), 0, null, null));
            add(new Site(800, "El Androide Libre", 0xffa3c23e, assets.open("elandroidelibre.png"), new com.newsup.kernel.section.ElAndroideLibreSections()));
            add(new Site(805, "Digital Trends", 0xff0098d9, assets.open("digitaltrends.png"), new com.newsup.kernel.section.DigitalTrendsSections()));
            add(new Site(810, "Lifehacker", 0xff94b330, assets.open("lifehacker.png"), new com.newsup.kernel.section.LifeHackerSections()));
            add(new Site(815, "Xataka", 0xff558f22, assets.open("xataka.png"), new com.newsup.kernel.section.XatakaSections()));
            add(new Site(820, "TED", 0xffffffff, assets.open("ted.png"), new com.newsup.kernel.section.TEDSections()));
            add(new Site(825, "Gizmodo", 0xff9c9c9c, assets.open("gizmodo.png"), new com.newsup.kernel.section.GizmodoSections()));
            add(new Site(830, "Android Authority", 0xff8cc234, assets.open("androidauthority.png"), new com.newsup.kernel.section.AndroidAuthoritySections()));
            add(new Site(835, "Computer Hoy", 0xff1a1a1a, assets.open("computerhoy.png"), new com.newsup.kernel.section.ComputerHoySections()));
//          add(new Site(840, "Swedroid", 0xff, assets.open("swedroid.png"), new com.newsup.kernel.section.SwedroidSections()));

            add(new Site(-1, context.getString(R.string.blogs), 0, null, null));
            add(new Site(900, "Medium", 0xffffffff, assets.open("medium.png"), new com.newsup.kernel.section.MediumSections()));

            add(new Site(-1, context.getString(R.string.magazines), 0, null, null));
            add(new Site(1000, "Make", 0xff4ecbf5, assets.open("make.png"), new com.newsup.kernel.section.MakeSections()));
            add(new Site(1005, "Discover", 0xff171717, assets.open("discover.png"), new com.newsup.kernel.section.DiscoverSections()));
            add(new Site(1010, "Rolling Stone", 0xff1c202b, assets.open("rollingstone.png"), new com.newsup.kernel.section.RollingStoneSections()));
            add(new Site(1015, "People", 0xff20b3e8, assets.open("people.png"), new com.newsup.kernel.section.PeopleSections()));
            add(new Site(1020, "Time", 0xffe60000, assets.open("time.png"), new com.newsup.kernel.section.TimeSections()));
            add(new Site(1025, "The Atlantic", 0xff030202, assets.open("theatlantic.png"), new com.newsup.kernel.section.TheAtlanticSections()));
            add(new Site(1030, "Sky and Telescope", 0xffd92326, assets.open("skyntelescope.png"), new com.newsup.kernel.section.SkyAndTelescopeSections()));
            add(new Site(1035, "Dogster", 0xff547a94, assets.open("dogster.png"), new com.newsup.kernel.section.DogsterSections()));
            add(new Site(1040, "El Jueves", 0xffcb1f1f, assets.open("eljueves.png"), new com.newsup.kernel.section.ElJuevesSections()));
//          add(new Site(1045, "National Geographic", 0xff, assets.open("nationalgeographic.png"), new com.newsup.kernel.section.NationalGeographicSections()));
//          add(new Site(1050, "Space News", 0xff, assets.open("spacenews.png"), new com.newsup.kernel.section.SpaceNewsSections()));
//          add(new Site(1055, "Space", 0xff, assets.open("space.png"), new com.newsup.kernel.section.SpaceSections()));
//          add(new Site(1060, "The Bark", 0xff, assets.open("thebark.png"), new com.newsup.kernel.section.TheBarkSections()));

        } catch (Exception e) {
            android.util.Log.d("##SiteList##", "No se ha encontrado un logo");
            e.printStackTrace();
        }
    }

    public SiteList(TreeSet<Site> list) {
        super(list);
    }

    public Site getSiteByCode(int code) {
        for (Site site : this) {
            if (site.code == code) {
                return site;
            }
        }
        return null;
    }
}
