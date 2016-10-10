package com.lucevent.newsup.data;

import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.SiteCategory;
import com.lucevent.newsup.data.util.SiteCountry;
import com.lucevent.newsup.data.util.SiteLanguage;

import java.util.ArrayList;
import java.util.Collection;

public class Sites extends ArrayList<Site> {

    public Sites()
    {
        super();
    }

    public Sites(Collection<Site> c)
    {
        super(c);
    }

    public Sites(int capacity)
    {
        super(capacity);
    }

    public static Sites getDefault(boolean finnishSites)
    {
        Sites res = new Sites(60);
        // Spanish newspapers
        res.add(new Site(100, "El Pais", 0xffffffff, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.ElPaisSections(), new com.lucevent.newsup.data.reader.ElPais()));
        res.add(new Site(105, "20 Minutos", 0xff004594, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section._20MinutosSections(), new com.lucevent.newsup.data.reader._20Minutos()));
        res.add(new Site(110, "El Mundo", 0xffffffff, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.ElMundoSections(), new com.lucevent.newsup.data.reader.ElMundo()));
        res.add(new Site(115, "As", 0xffba0202, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.SPORT_NEWSPAPER,
                new com.lucevent.newsup.data.section.AsSections(), new com.lucevent.newsup.data.reader.As()));
        res.add(new Site(120, "Marca", 0xff04394a, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.SPORT_NEWSPAPER,
                new com.lucevent.newsup.data.section.MarcaSections(), new com.lucevent.newsup.data.reader.Marca()));
        res.add(new Site(125, "El Confidencial", 0xff145f85, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.ElConfidencialSections(), new com.lucevent.newsup.data.reader.ElConfidencial()));
        res.add(new Site(130, "El Diario", 0xff0061ab, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.ElDiarioSections(), new com.lucevent.newsup.data.reader.ElDiario()));
        res.add(new Site(135, "La Raz\u00F3n", 0xffc7c7c7, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.LaRazonSections(), new com.lucevent.newsup.data.reader.LaRazon()));
        res.add(new Site(140, "Huffington Post Espa\u00F1a", 0xff2c705f, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.HuffingtonPostSpainSections(), new com.lucevent.newsup.data.reader.HuffingtonPostSpain()));
        res.add(new Site(145, "Europa press", 0xffffffff, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.EuropaPressSections(), new com.lucevent.newsup.data.reader.EuropaPress()));
        res.add(new Site(150, "Diario C\u00F3rdoba", 0xffde2632, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.DiarioCordobaSections(), new com.lucevent.newsup.data.reader.DiarioCordoba()));
        res.add(new Site(155, "Abc", 0xff21435c, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.AbcSections(), new com.lucevent.newsup.data.reader.Abc()));

        // Catalan newspapers
        res.add(new Site(200, "El Peri\u00F3dico (Cat)", 0xff0088c7, SiteCountry.SPAIN | SiteLanguage.CATALAN | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.ElPeriodicoCaSections(), new com.lucevent.newsup.data.reader.ElPeriodicoCa()));
        res.add(new Site(205, "El Peri\u00F3dico (Esp)", 0xfff04d4d, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.ElPeriodicoEsSections(), new com.lucevent.newsup.data.reader.ElPeriodicoEs()));
        res.add(new Site(210, "La Vanguardia", 0xff1a4970, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.LaVanguardiaSections(), new com.lucevent.newsup.data.reader.LaVanguardia()));
        res.add(new Site(215, "Sport", 0xffd61a1a, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.SPORT_NEWSPAPER,
                new com.lucevent.newsup.data.section.SportSections(), new com.lucevent.newsup.data.reader.Sport()));
        res.add(new Site(220, "Mundo Deportivo", 0xff242424, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.SPORT_NEWSPAPER,
                new com.lucevent.newsup.data.section.MundoDeportivoSections(), new com.lucevent.newsup.data.reader.MundoDeportivo()));
        res.add(new Site(225, "Rac\u00F3 Catal\u00E0", 0xffff6347, SiteCountry.SPAIN | SiteLanguage.CATALAN | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.RacoCatalaSections(), new com.lucevent.newsup.data.reader.RacoCatala()));
        res.add(new Site(230, "VilaWeb", 0xfffd6300, SiteCountry.SPAIN | SiteLanguage.CATALAN | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.VilaWebSections(), new com.lucevent.newsup.data.reader.VilaWeb()));

        // Swedish newspapers
        res.add(new Site(300, "Aftonbladet", 0xffffffff, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.AftonbladetSections(), new com.lucevent.newsup.data.reader.Aftonbladet()));
        res.add(new Site(305, "Expressen", 0xffdb2727, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.ExpressenSections(), new com.lucevent.newsup.data.reader.Expressen()));
        res.add(new Site(310, "Dagens Nyheter", 0xffeb1c2a, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.DagensNyheterSections(), new com.lucevent.newsup.data.reader.DagensNyheter()));
        res.add(new Site(315, "Svenska Dagbladet", 0xfff5f5f5, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.SvenskaDagbladetSections(), new com.lucevent.newsup.data.reader.SvenskaDagbladet()));
        res.add(new Site(325, "Fria Tider", 0xffffffff, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.FriaTiderSections(), new com.lucevent.newsup.data.reader.FriaTider()));
        res.add(new Site(330, "Metro", 0xff007d3c, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.MetroSVSections(), new com.lucevent.newsup.data.reader.MetroSV()));

        // Finnish newspapers
        if (finnishSites) {
            res.add(new Site(400, "Helsinki times", 0xff32c8fa, SiteCountry.FINLAND | SiteLanguage.ENGLISH | SiteCategory.NEWSPAPER,
                    new com.lucevent.newsup.data.section.HelsinkiTimesSections(), new com.lucevent.newsup.data.reader.HelsinkiTimes()));
            res.add(new Site(405, "Helsingin Sanomat", 0xff01133d, SiteCountry.FINLAND | SiteLanguage.FINNISH | SiteCategory.NEWSPAPER,
                    new com.lucevent.newsup.data.section.HelsinkiSanomatSections(), new com.lucevent.newsup.data.reader.HelsinkiSanomat()));
            res.add(new Site(410, "Iltalehti", 0xffff0000, SiteCountry.FINLAND | SiteLanguage.FINNISH | SiteCategory.NEWSPAPER,
                    new com.lucevent.newsup.data.section.IltalehtiSections(), new com.lucevent.newsup.data.reader.Iltalehti()));
            res.add(new Site(415, "Yle", 0xff00b4c4, SiteCountry.FINLAND | SiteLanguage.FINNISH | SiteCategory.NEWSPAPER,
                    new com.lucevent.newsup.data.section.YleSections(), new com.lucevent.newsup.data.reader.Yle()));
        }
//        res.add(new Site(420, "Yle Svenska", 0xff, new com.lucevent.newsup.data.section.YleSvenskaSections(), new com.lucevent.newsup.data.reader.YleSvenska()));

        // British newspapers
        res.add(new Site(500, "BBC", 0xffa62e30, SiteCountry.UK | SiteLanguage.ENGLISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.BBCSections(), new com.lucevent.newsup.data.reader.BBC()));
        res.add(new Site(505, "The Telegraph", 0xffffffff, SiteCountry.UK | SiteLanguage.ENGLISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.TheTelegraphSections(), new com.lucevent.newsup.data.reader.TheTelegraph()));
        res.add(new Site(510, "The Huffington Post UK", 0xff2c705f, SiteCountry.UK | SiteLanguage.ENGLISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.HuffingtonPostUKSections(), new com.lucevent.newsup.data.reader.HuffingtonPostUK()));
        res.add(new Site(515, "Metro UK", 0xfff78b26, SiteCountry.UK | SiteLanguage.ENGLISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.MetroUKSections(), new com.lucevent.newsup.data.reader.MetroUK()));
        res.add(new Site(520, "The Guardian", 0xff005689, SiteCountry.UK | SiteLanguage.ENGLISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.TheGuardianSections(), new com.lucevent.newsup.data.reader.TheGuardian()));
        res.add(new Site(525, "The Herald Scotland", 0xffffffff, SiteCountry.UK | SiteLanguage.ENGLISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.TheHeraldScotlandSections(), new com.lucevent.newsup.data.reader.TheHeraldScotland()));

        // American newspapers
        res.add(new Site(600, "CNN", 0xffc20000, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.CNNSections(), new com.lucevent.newsup.data.reader.CNN()));
        res.add(new Site(605, "The Huffington Post USA", 0xff2c705f, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.HuffingtonPostUSASections(), new com.lucevent.newsup.data.reader.HuffingtonPostInt()));
        res.add(new Site(610, "USA Today", 0xff009bff, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.USATodaySections(), new com.lucevent.newsup.data.reader.USAToday()));
        res.add(new Site(615, "The New York Times", 0xffffffff, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.TheNewYorkTimesSections(), new com.lucevent.newsup.data.reader.TheNewYorkTimes()));

        //Other newspapers
        res.add(new Site(700, "The Local", 0xfff76e05, SiteCountry.EUROPE | SiteLanguage.ENGLISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.TheLocalSections(), new com.lucevent.newsup.data.reader.TheLocal()));
        res.add(new Site(715, "The Huffington Post Canada", 0xff2c705f, SiteCountry.CANADA | SiteLanguage.ENGLISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.HuffingtonPostCanadaSections(), new com.lucevent.newsup.data.reader.HuffingtonPostInt()));
        res.add(new Site(720, "The Siberian Times", 0xff0271b9, SiteCountry.RUSSIA | SiteLanguage.ENGLISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.SiberianTimesSections(), new com.lucevent.newsup.data.reader.SiberianTimes()));
        res.add(new Site(725, "The Times of India", 0xff8e1e1d, SiteCountry.INDIA | SiteLanguage.ENGLISH | SiteCategory.NEWSPAPER,
                new com.lucevent.newsup.data.section.TheTimesOfIndiaSections(), new com.lucevent.newsup.data.reader.TheTimesOfIndia()));
//        res.add(new Site(705, "The Huffington Post (Australia)", 0xff2c705f, new com.lucevent.newsup.data.section.HuffingtonPostAustraliaSections(), new com.lucevent.newsup.data.reader.HuffingtonPostAustralia()));
//        res.add(new Site(710, "The Huffington Post (Brasil)", 0xff2c705f, new com.lucevent.newsup.data.section.HuffingtonPostBrasilSections(), new com.lucevent.newsup.data.reader.HuffingtonPostBrasil()));

        // Technology sites
        res.add(new Site(800, "El Androide Libre", 0xffa3c23e, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.TECHNOLOGY,
                new com.lucevent.newsup.data.section.ElAndroideLibreSections(), new com.lucevent.newsup.data.reader.ElAndroideLibre()));
        res.add(new Site(805, "Digital Trends", 0xff0098d9, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                new com.lucevent.newsup.data.section.DigitalTrendsSections(), new com.lucevent.newsup.data.reader.DigitalTrends()));
        res.add(new Site(810, "Lifehacker", 0xff94b330, SiteCountry.SPAIN | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                new com.lucevent.newsup.data.section.LifeHackerSections(), new com.lucevent.newsup.data.reader.LifeHacker()));
        res.add(new Site(815, "Xataka", 0xff558f22, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.TECHNOLOGY,
                new com.lucevent.newsup.data.section.XatakaSections(), new com.lucevent.newsup.data.reader.Xataka()));
        res.add(new Site(820, "TED", 0xffffffff, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.SCIENCE,
                new com.lucevent.newsup.data.section.TEDSections(), new com.lucevent.newsup.data.reader.TED()));
        res.add(new Site(825, "Gizmodo", 0xff9c9c9c, SiteCountry.EUROPE | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                new com.lucevent.newsup.data.section.GizmodoSections(), new com.lucevent.newsup.data.reader.Gizmodo()));
        res.add(new Site(830, "Android Authority", 0xff8cc234, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                new com.lucevent.newsup.data.section.AndroidAuthoritySections(), new com.lucevent.newsup.data.reader.AndroidAuthority()));
        res.add(new Site(835, "Computer Hoy", 0xff1a1a1a, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.TECHNOLOGY,
                new com.lucevent.newsup.data.section.ComputerHoySections(), new com.lucevent.newsup.data.reader.ComputerHoy()));
        res.add(new Site(840, "Swedroid", 0xff485366, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.TECHNOLOGY,
                new com.lucevent.newsup.data.section.SwedroidSections(), new com.lucevent.newsup.data.reader.Swedroid()));
        res.add(new Site(845, "Hipertextual", 0xff2799d7, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.TECHNOLOGY,
                new com.lucevent.newsup.data.section.HipertextualSections(), new com.lucevent.newsup.data.reader.Hipertextual()));
        res.add(new Site(850, "Mashable", 0xff01aef0, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                new com.lucevent.newsup.data.section.MashableSections(), new com.lucevent.newsup.data.reader.Mashable()));
        res.add(new Site(855, "PC World", 0xff941622, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                new com.lucevent.newsup.data.section.PCWorldSections(), new com.lucevent.newsup.data.reader.PCWorld()));
        res.add(new Site(860, "The Verge", 0xfffa4b2a, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                new com.lucevent.newsup.data.section.TheVergeSections(), new com.lucevent.newsup.data.reader.TheVerge()));
        res.add(new Site(865, "Clipset", 0xff000000, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.TECHNOLOGY,
                new com.lucevent.newsup.data.section.ClipsetSections(), new com.lucevent.newsup.data.reader.Clipset()));
        res.add(new Site(870, "TechCrunch", 0xff1a9711, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                new com.lucevent.newsup.data.section.TechCrunchSections(), new com.lucevent.newsup.data.reader.TechCrunch()));

        // Blog sites
        res.add(new Site(900, "Medium", 0xffffffff, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.BLOG,
                new com.lucevent.newsup.data.section.MediumSections(), new com.lucevent.newsup.data.reader.Medium()));
        res.add(new Site(905, "Verne", 0xff02b283, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.BLOG,
                new com.lucevent.newsup.data.section.VerneSections(), new com.lucevent.newsup.data.reader.Verne()));
        res.add(new Site(910, "Digital Inspiration", 0xff000000, SiteCountry.INDIA | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                new com.lucevent.newsup.data.section.DigitalInspirationSections(), new com.lucevent.newsup.data.reader.DigitalInspiration()));
        res.add(new Site(915, "Google Earth Blog", 0xffffffff, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.BLOG,
                new com.lucevent.newsup.data.section.GoogleEarthBlogSections(), new com.lucevent.newsup.data.reader.GoogleEarthBlog()));

        // Magazines sites (1000)
        res.add(new Site(1010, "Rolling Stone", 0xff1c202b, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.MAGAZINE,
                new com.lucevent.newsup.data.section.RollingStoneSections(), new com.lucevent.newsup.data.reader.RollingStone()));
        res.add(new Site(1015, "People", 0xff20b3e8, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.MAGAZINE,
                new com.lucevent.newsup.data.section.PeopleSections(), new com.lucevent.newsup.data.reader.People()));
        res.add(new Site(1020, "Time", 0xffe60000, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.MAGAZINE,
                new com.lucevent.newsup.data.section.TimeSections(), new com.lucevent.newsup.data.reader.Time()));
        res.add(new Site(1025, "The Atlantic", 0xff030202, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.MAGAZINE,
                new com.lucevent.newsup.data.section.TheAtlanticSections(), new com.lucevent.newsup.data.reader.TheAtlantic()));
        res.add(new Site(1000, "Make", 0xff4ecbf5, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.MAGAZINE,
                new com.lucevent.newsup.data.section.MakeSections(), new com.lucevent.newsup.data.reader.Make()));
        res.add(new Site(1005, "Discover", 0xff171717, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.MAGAZINE,
                new com.lucevent.newsup.data.section.DiscoverSections(), new com.lucevent.newsup.data.reader.Discover()));
        res.add(new Site(1045, "Digital Camera", 0xffffffff, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.MAGAZINE,
                new com.lucevent.newsup.data.section.DigitalCameraSections(), new com.lucevent.newsup.data.reader.DigitalCamera()));
        res.add(new Site(1060, "Elle", 0xffffffff, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.MAGAZINE,
                new com.lucevent.newsup.data.section.ElleSections(), new com.lucevent.newsup.data.reader.Elle()));
        res.add(new Site(1065, "The Intercept", 0xff000000, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.MAGAZINE,
                new com.lucevent.newsup.data.section.TheInterceptSections(), new com.lucevent.newsup.data.reader.TheIntercept()));

        res.add(new Site(1035, "Dogster", 0xff547a94, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.MAGAZINE,
                new com.lucevent.newsup.data.section.DogsterSections(), new com.lucevent.newsup.data.reader.Dogster()));
//        res.add(new Site(, "The Bark", 0xff, new com.lucevent.newsup.data.section.TheBarkSections(), new com.lucevent.newsup.data.reader.TheBark()));

        res.add(new Site(1040, "El Jueves", 0xffcb1f1f, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.MAGAZINE,
                new com.lucevent.newsup.data.section.ElJuevesSections(), new com.lucevent.newsup.data.reader.ElJueves()));
//        res.add(new Site(, "National Geographic", 0xff, new com.lucevent.newsup.data.section.NationalGeographicSections(), new com.lucevent.newsup.data.reader.NationalGeographic()));

        // Astronomy (1200)
        res.add(new Site(1030, "Sky and Telescope", 0xffd92326, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.ASTRONOMY,
                new com.lucevent.newsup.data.section.SkyAndTelescopeSections(), new com.lucevent.newsup.data.reader.SkyAndTelescope()));
        res.add(new Site(1050, "Space News", 0xffffffff, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.ASTRONOMY,
                new com.lucevent.newsup.data.section.SpaceNewsSections(), new com.lucevent.newsup.data.reader.SpaceNews()));
        //       res.add(new Site(1210, "Space.com", 0xff000000, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.ASTRONOMY,
        //               new com.lucevent.newsup.data.section.SpaceSections(), new com.lucevent.newsup.data.reader.Space()));
        //      res.add(new Site(1215, "NASA", 0xffxxxxxx, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.ASTRONOMY,
        //              new com.lucevent.newsup.data.section.NASASections(), new com.lucevent.newsup.data.reader.NASA()));

        // Videogames (1300)
        res.add(new Site(1300, "Meristation", 0xffff6a00, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.VIDEOGAMES,
                new com.lucevent.newsup.data.section.MeristationSections(), new com.lucevent.newsup.data.reader.Meristation()));
        res.add(new Site(1305, "Vandal", 0xffffffff, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.VIDEOGAMES,
                new com.lucevent.newsup.data.section.VandalSections(), new com.lucevent.newsup.data.reader.Vandal()));

        // LifeStyle (1400)
        res.add(new Site(1400, "Vice", 0xff000000, SiteCountry.WORLD | SiteLanguage.MANY | SiteCategory.LIFESTYLE,
                new com.lucevent.newsup.data.section.ViceSections(), new com.lucevent.newsup.data.reader.Vice()));

        //Not working
//        res.add(new Site(320, "Goteborgs Posten", 0xff005c9e, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.NEWSPAPER,
//                new com.lucevent.newsup.data.section.GoteborgsPostenSections(), new com.lucevent.newsup.data.reader.GoteborgsPosten()));

        return res;
    }

    public Site getSiteByCode(int code)
    {
        for (Site site : this)
            if (site.code == code)
                return site;

        return null;
    }

    public int getIndexByCode(int code)
    {
        for (int i = 0; i < size(); i++) {
            Site site = get(i);
            if (site.code == code)
                return i;
        }
        return -1;
    }

}