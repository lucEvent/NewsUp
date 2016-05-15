package com.lucevent.newsup.data;

import com.lucevent.newsup.data.util.Site;

import java.util.ArrayList;
import java.util.Collection;

public class Sites extends ArrayList<Site> {

    public Sites(String[] titles) //new String[]{R.string.spain, R.string.catalonia, ....};
    {
        super();

        addDefault(titles);
    }

    public Sites(Collection<Site> c)
    {
        super(c);
    }

    public Sites(int capacity)
    {
        super(capacity);
    }

    private void addDefault(String[] titles)
    {
        // Spanish sites
        if (titles != null) add(new Site(-1, titles[0], -1, null, null));
        add(new Site(100, "El Pais", 0xffffffff, new com.lucevent.newsup.data.section.ElPaisSections(), new com.lucevent.newsup.data.reader.ElPais()));
        add(new Site(105, "20 Minutos", 0xff004594, new com.lucevent.newsup.data.section._20MinutosSections(), new com.lucevent.newsup.data.reader._20Minutos()));
        add(new Site(110, "El Mundo", 0xffffffff, new com.lucevent.newsup.data.section.ElMundoSections(), new com.lucevent.newsup.data.reader.ElMundo()));
        add(new Site(115, "As", 0xffba0202, new com.lucevent.newsup.data.section.AsSections(), new com.lucevent.newsup.data.reader.As()));
        add(new Site(120, "Marca", 0xff04394a, new com.lucevent.newsup.data.section.MarcaSections(), new com.lucevent.newsup.data.reader.Marca()));
        add(new Site(125, "El Confidencial", 0xff145f85, new com.lucevent.newsup.data.section.ElConfidencialSections(), new com.lucevent.newsup.data.reader.ElConfidencial()));
        add(new Site(130, "El Diario", 0xff0061ab, new com.lucevent.newsup.data.section.ElDiarioSections(), new com.lucevent.newsup.data.reader.ElDiario()));
        add(new Site(135, "La Razón", 0xffc7c7c7, new com.lucevent.newsup.data.section.LaRazonSections(), new com.lucevent.newsup.data.reader.LaRazon()));
        add(new Site(140, "Huffington Post España", 0xff2c705f, new com.lucevent.newsup.data.section.HuffingtonPostSpainSections(), new com.lucevent.newsup.data.reader.HuffingtonPostSpain()));
        add(new Site(145, "Europa press", 0xffffffff, new com.lucevent.newsup.data.section.EuropaPressSections(), new com.lucevent.newsup.data.reader.EuropaPress()));
        add(new Site(150, "Diario Córdoba", 0xffde2632, new com.lucevent.newsup.data.section.DiarioCordobaSections(), new com.lucevent.newsup.data.reader.DiarioCordoba()));

        // Catalan sites
        if (titles != null) add(new Site(-1, titles[1], -1, null, null));
        add(new Site(200, "El Periódico (Cat)", 0xff0088c7, new com.lucevent.newsup.data.section.ElPeriodicoCaSections(), new com.lucevent.newsup.data.reader.ElPeriodicoCa()));
        add(new Site(205, "El Periódico (Esp)", 0xfff04d4d, new com.lucevent.newsup.data.section.ElPeriodicoEsSections(), new com.lucevent.newsup.data.reader.ElPeriodicoEs()));
        add(new Site(210, "La Vanguardia", 0xff1a4970, new com.lucevent.newsup.data.section.LaVanguardiaSections(), new com.lucevent.newsup.data.reader.LaVanguardia()));
        add(new Site(215, "Sport", 0xffd61a1a, new com.lucevent.newsup.data.section.SportSections(), new com.lucevent.newsup.data.reader.Sport()));
        add(new Site(220, "Mundo Deportivo", 0xff242424, new com.lucevent.newsup.data.section.MundoDeportivoSections(), new com.lucevent.newsup.data.reader.MundoDeportivo()));
        add(new Site(225, "Racò Català", 0xffff6347, new com.lucevent.newsup.data.section.RacoCatalaSections(), new com.lucevent.newsup.data.reader.RacoCatala()));

        // Swedish sites
        if (titles != null) add(new Site(-1, titles[2], -1, null, null));
        add(new Site(300, "Aftonbladet", 0xffffffff, new com.lucevent.newsup.data.section.AftonbladetSections(), new com.lucevent.newsup.data.reader.Aftonbladet()));
        add(new Site(305, "Expressen", 0xffdb2727, new com.lucevent.newsup.data.section.ExpressenSections(), new com.lucevent.newsup.data.reader.Expressen()));
        add(new Site(315, "Svenska Dagbladet", 0xfff5f5f5, new com.lucevent.newsup.data.section.SvenskaDagbladetSections(), new com.lucevent.newsup.data.reader.SvenskaDagbladet()));
        add(new Site(325, "Fria Tider", 0xffffffff, new com.lucevent.newsup.data.section.FriaTiderSections(), new com.lucevent.newsup.data.reader.FriaTider()));
        add(new Site(330, "Metro", 0xff007d3c, new com.lucevent.newsup.data.section.MetroSections(), new com.lucevent.newsup.data.reader.Metro()));

        // Finnish sites
        if (titles != null) add(new Site(-1, titles[3], -1, null, null));
        add(new Site(400, "Helsinki times", 0xff32c8fa, new com.lucevent.newsup.data.section.HelsinkiTimesSections(), new com.lucevent.newsup.data.reader.HelsinkiTimes()));
        add(new Site(405, "Helsingin Sanomat", 0xff01133d, new com.lucevent.newsup.data.section.HelsinkiSanomatSections(), new com.lucevent.newsup.data.reader.HelsinkiSanomat()));
        add(new Site(410, "Iltalehti", 0xffff0000, new com.lucevent.newsup.data.section.IltalehtiSections(), new com.lucevent.newsup.data.reader.Iltalehti()));
        add(new Site(415, "Yle", 0xff00b4c4, new com.lucevent.newsup.data.section.YleSections(), new com.lucevent.newsup.data.reader.Yle()));
//        add(new Site(420, "Yle Svenska", 0xff, new com.lucevent.newsup.data.section.YleSvenskaSections(), new com.lucevent.newsup.data.reader.YleSvenska()));

        // British sites
        if (titles != null) add(new Site(-1, titles[4], -1, null, null));
        add(new Site(500, "BBC", 0xffa62e30, new com.lucevent.newsup.data.section.BBCSections(), new com.lucevent.newsup.data.reader.BBC()));
        add(new Site(510, "The Huffington Post UK", 0xff2c705f, new com.lucevent.newsup.data.section.HuffingtonPostUKSections(), new com.lucevent.newsup.data.reader.HuffingtonPostUK()));

        // American sites
        if (titles != null) add(new Site(-1, titles[5], -1, null, null));
        add(new Site(600, "CNN", 0xffc20000, new com.lucevent.newsup.data.section.CNNSections(), new com.lucevent.newsup.data.reader.CNN()));
        add(new Site(605, "The Huffington Post USA", 0xff2c705f, new com.lucevent.newsup.data.section.HuffingtonPostUSASections(), new com.lucevent.newsup.data.reader.HuffingtonPostInt()));

        //Other sites
        if (titles != null) add(new Site(-1, titles[6], -1, null, null));
        add(new Site(700, "The Local", 0xfff76e05, new com.lucevent.newsup.data.section.TheLocalSections(), new com.lucevent.newsup.data.reader.TheLocal()));
//        add(new Site(705, "The Huffington Post (Australia)", 0xff2c705f, new com.lucevent.newsup.data.section.HuffingtonPostAustraliaSections(), new com.lucevent.newsup.data.reader.HuffingtonPostAustralia()));
//        add(new Site(710, "The Huffington Post (Brasil)", 0xff2c705f, new com.lucevent.newsup.data.section.HuffingtonPostBrasilSections(), new com.lucevent.newsup.data.reader.HuffingtonPostBrasil()));
        add(new Site(715, "The Huffington Post Canada", 0xff2c705f, new com.lucevent.newsup.data.section.HuffingtonPostCanadaSections(), new com.lucevent.newsup.data.reader.HuffingtonPostInt()));

        // Technology sites
        if (titles != null) add(new Site(-1, titles[7], -1, null, null));
        add(new Site(800, "El Androide Libre", 0xffa3c23e, new com.lucevent.newsup.data.section.ElAndroideLibreSections(), new com.lucevent.newsup.data.reader.ElAndroideLibre()));
        add(new Site(805, "Digital Trends", 0xff0098d9, new com.lucevent.newsup.data.section.DigitalTrendsSections(), new com.lucevent.newsup.data.reader.DigitalTrends()));
        add(new Site(810, "Lifehacker", 0xff94b330, new com.lucevent.newsup.data.section.LifeHackerSections(), new com.lucevent.newsup.data.reader.LifeHacker()));
        add(new Site(815, "Xataka", 0xff558f22, new com.lucevent.newsup.data.section.XatakaSections(), new com.lucevent.newsup.data.reader.Xataka()));
        add(new Site(820, "TED", 0xffffffff, new com.lucevent.newsup.data.section.TEDSections(), new com.lucevent.newsup.data.reader.TED()));
        add(new Site(825, "Gizmodo", 0xff9c9c9c, new com.lucevent.newsup.data.section.GizmodoSections(), new com.lucevent.newsup.data.reader.Gizmodo()));
        add(new Site(830, "Android Authority", 0xff8cc234, new com.lucevent.newsup.data.section.AndroidAuthoritySections(), new com.lucevent.newsup.data.reader.AndroidAuthority()));
        add(new Site(835, "Computer Hoy", 0xff1a1a1a, new com.lucevent.newsup.data.section.ComputerHoySections(), new com.lucevent.newsup.data.reader.ComputerHoy()));
        add(new Site(840, "Swedroid", 0xff485366, new com.lucevent.newsup.data.section.SwedroidSections(), new com.lucevent.newsup.data.reader.Swedroid()));

        // Blog sites
        if (titles != null) add(new Site(-1, titles[8], -1, null, null));
        add(new Site(900, "Medium", 0xffffffff, new com.lucevent.newsup.data.section.MediumSections(), new com.lucevent.newsup.data.reader.Medium()));

        // Magazines sites
        if (titles != null) add(new Site(-1, titles[9], -1, null, null));
        add(new Site(1010, "Rolling Stone", 0xff1c202b, new com.lucevent.newsup.data.section.RollingStoneSections(), new com.lucevent.newsup.data.reader.RollingStone()));
        add(new Site(1015, "People", 0xff20b3e8, new com.lucevent.newsup.data.section.PeopleSections(), new com.lucevent.newsup.data.reader.People()));
        add(new Site(1020, "Time", 0xffe60000, new com.lucevent.newsup.data.section.TimeSections(), new com.lucevent.newsup.data.reader.Time()));
        add(new Site(1025, "The Atlantic", 0xff030202, new com.lucevent.newsup.data.section.TheAtlanticSections(), new com.lucevent.newsup.data.reader.TheAtlantic()));

        add(new Site(1000, "Make", 0xff4ecbf5, new com.lucevent.newsup.data.section.MakeSections(), new com.lucevent.newsup.data.reader.Make()));
        add(new Site(1045, "Digital Camera", 0xffffffff, new com.lucevent.newsup.data.section.DigitalCameraSections(), new com.lucevent.newsup.data.reader.DigitalCamera()));

        add(new Site(1030, "Sky and Telescope", 0xffd92326, new com.lucevent.newsup.data.section.SkyAndTelescopeSections(), new com.lucevent.newsup.data.reader.SkyAndTelescope()));
        add(new Site(1050, "Space News", 0xffffffff, new com.lucevent.newsup.data.section.SpaceNewsSections(), new com.lucevent.newsup.data.reader.SpaceNews()));

        add(new Site(1035, "Dogster", 0xff547a94, new com.lucevent.newsup.data.section.DogsterSections(), new com.lucevent.newsup.data.reader.Dogster()));

        add(new Site(1040, "El Jueves", 0xffcb1f1f, new com.lucevent.newsup.data.section.ElJuevesSections(), new com.lucevent.newsup.data.reader.ElJueves()));


//        add(new Site(, "National Geographic", 0xff, new com.lucevent.newsup.data.section.NationalGeographicSections(), new com.lucevent.newsup.data.reader.NationalGeographic()));
//        add(new Site(, "Space", 0xff, new com.lucevent.newsup.data.section.SpaceSections(), new com.lucevent.newsup.data.reader.Space()));
//        add(new Site(, "The Bark", 0xff, new com.lucevent.newsup.data.section.TheBarkSections(), new com.lucevent.newsup.data.reader.TheBark()));

        //Not working
        if (titles != null) add(new Site(-1, titles[10], -1, null, null));
        add(new Site(310, "Dagens Nyheter", 0xffeb1c2a, new com.lucevent.newsup.data.section.DagensNyheterSections(), new com.lucevent.newsup.data.reader.DagensNyheter()));
        add(new Site(320, "Goteborgs Posten", 0xff005c9e, new com.lucevent.newsup.data.section.GoteborgsPostenSections(), new com.lucevent.newsup.data.reader.GoteborgsPosten()));
        add(new Site(505, "The Telegraph", 0xffffffff, new com.lucevent.newsup.data.section.TheTelegraphSections(), new com.lucevent.newsup.data.reader.TheTelegraph()));
        add(new Site(1005, "Discover", 0xff171717, new com.lucevent.newsup.data.section.DiscoverSections(), new com.lucevent.newsup.data.reader.Discover()));
    }

    public Site getSiteByCode(int code)
    {
        for (Site site : this)
            if (site.code == code)
                return site;

        return null;
    }

}
