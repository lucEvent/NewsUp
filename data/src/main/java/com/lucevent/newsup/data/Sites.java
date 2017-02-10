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
        Sites res = new Sites(100);

        // Spanish news
        res.add(new Site(100, "El Pa\u00EDs", 0xffffffff, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.ElPaisSections.class, com.lucevent.newsup.data.reader.ElPais.class));
        res.add(new Site(105, "20 Minutos", 0xff004594, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section._20MinutosSections.class, com.lucevent.newsup.data.reader._20Minutos.class));
        res.add(new Site(110, "El Mundo", 0xffffffff, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.ElMundoSections.class, com.lucevent.newsup.data.reader.ElMundo.class));
        res.add(new Site(115, "As", 0xffba0202, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.SPORTS_NEWS,
                com.lucevent.newsup.data.section.AsSections.class, com.lucevent.newsup.data.reader.As.class));
        res.add(new Site(120, "Marca", 0xff04394a, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.SPORTS_NEWS,
                com.lucevent.newsup.data.section.MarcaSections.class, com.lucevent.newsup.data.reader.Marca.class));
        res.add(new Site(125, "El Confidencial", 0xff145f85, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.ElConfidencialSections.class, com.lucevent.newsup.data.reader.ElConfidencial.class));
        res.add(new Site(130, "El Diario", 0xff0061ab, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.ElDiarioSections.class, com.lucevent.newsup.data.reader.ElDiario.class));
        res.add(new Site(135, "La Raz\u00F3n", 0xffc7c7c7, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.LaRazonSections.class, com.lucevent.newsup.data.reader.LaRazon.class));
        res.add(new Site(140, "Huffington Post Espa\u00F1a", 0xff2c705f, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.HuffingtonPostSpainSections.class, com.lucevent.newsup.data.reader.HuffingtonPostSpain.class));
        res.add(new Site(145, "Europa press", 0xffffffff, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.EuropaPressSections.class, com.lucevent.newsup.data.reader.EuropaPress.class));
        res.add(new Site(150, "Diario C\u00F3rdoba", 0xffde2632, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.DiarioCordobaSections.class, com.lucevent.newsup.data.reader.DiarioCordoba.class));
        res.add(new Site(155, "Abc", 0xff21435c, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.AbcSections.class, com.lucevent.newsup.data.reader.Abc.class));

        // Catalan news
        res.add(new Site(200, "El Peri\u00F3dico (Cat)", 0xff0088c7, SiteCountry.SPAIN | SiteLanguage.CATALAN | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.ElPeriodicoCaSections.class, com.lucevent.newsup.data.reader.ElPeriodicoCa.class));
        res.add(new Site(205, "El Peri\u00F3dico (Esp)", 0xfff04d4d, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.ElPeriodicoEsSections.class, com.lucevent.newsup.data.reader.ElPeriodicoEs.class));
        res.add(new Site(210, "La Vanguardia", 0xff1a4970, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.LaVanguardiaSections.class, com.lucevent.newsup.data.reader.LaVanguardia.class));
        res.add(new Site(215, "Sport", 0xffd61a1a, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.SPORTS_NEWS,
                com.lucevent.newsup.data.section.SportSections.class, com.lucevent.newsup.data.reader.Sport.class));
        res.add(new Site(220, "Mundo Deportivo", 0xff242424, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.SPORTS_NEWS,
                com.lucevent.newsup.data.section.MundoDeportivoSections.class, com.lucevent.newsup.data.reader.MundoDeportivo.class));
        res.add(new Site(225, "Rac\u00F3 Catal\u00E0", 0xffff6347, SiteCountry.SPAIN | SiteLanguage.CATALAN | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.RacoCatalaSections.class, com.lucevent.newsup.data.reader.RacoCatala.class));
        res.add(new Site(230, "VilaWeb", 0xfffd6300, SiteCountry.SPAIN | SiteLanguage.CATALAN | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.VilaWebSections.class, com.lucevent.newsup.data.reader.VilaWeb.class));

        // Swedish news
        res.add(new Site(300, "Aftonbladet", 0xffffffff, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.AftonbladetSections.class, com.lucevent.newsup.data.reader.Aftonbladet.class));
        res.add(new Site(305, "Expressen", 0xffdb2727, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.ExpressenSections.class, com.lucevent.newsup.data.reader.Expressen.class));
        res.add(new Site(310, "Dagens Nyheter", 0xffeb1c2a, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.DagensNyheterSections.class, com.lucevent.newsup.data.reader.DagensNyheter.class));
        res.add(new Site(315, "Svenska Dagbladet", 0xffffffff, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.SvenskaDagbladetSections.class, com.lucevent.newsup.data.reader.SvenskaDagbladet.class));
        res.add(new Site(325, "Fria Tider", 0xffffffff, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.FriaTiderSections.class, com.lucevent.newsup.data.reader.FriaTider.class));
        res.add(new Site(330, "Metro", 0xff007d3c, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.MetroSVSections.class, com.lucevent.newsup.data.reader.MetroSV.class));

        // Finnish news
        if (finnishSites) {
            res.add(new Site(400, "Helsinki times", 0xff32c8fa, SiteCountry.FINLAND | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                    com.lucevent.newsup.data.section.HelsinkiTimesSections.class, com.lucevent.newsup.data.reader.HelsinkiTimes.class));
            res.add(new Site(405, "Helsingin Sanomat", 0xff01133d, SiteCountry.FINLAND | SiteLanguage.FINNISH | SiteCategory.NEWS,
                    com.lucevent.newsup.data.section.HelsinkiSanomatSections.class, com.lucevent.newsup.data.reader.HelsinkiSanomat.class));
            res.add(new Site(410, "Iltalehti", 0xffff0000, SiteCountry.FINLAND | SiteLanguage.FINNISH | SiteCategory.NEWS,
                    com.lucevent.newsup.data.section.IltalehtiSections.class, com.lucevent.newsup.data.reader.Iltalehti.class));
            res.add(new Site(415, "Yle", 0xff00b4c4, SiteCountry.FINLAND | SiteLanguage.FINNISH | SiteCategory.NEWS,
                    com.lucevent.newsup.data.section.YleSections.class, com.lucevent.newsup.data.reader.Yle.class));
        }

        // British news
        res.add(new Site(500, "BBC", 0xffa62e30, SiteCountry.UK | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.BBCSections.class, com.lucevent.newsup.data.reader.BBC.class));
        res.add(new Site(505, "The Telegraph", 0xffffffff, SiteCountry.UK | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.TheTelegraphSections.class, com.lucevent.newsup.data.reader.TheTelegraph.class));
        res.add(new Site(510, "The Huffington Post UK", 0xff2c705f, SiteCountry.UK | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.HuffingtonPostUKSections.class, com.lucevent.newsup.data.reader.HuffingtonPostUK.class));
        res.add(new Site(515, "Metro UK", 0xfff78b26, SiteCountry.UK | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.MetroUKSections.class, com.lucevent.newsup.data.reader.MetroUK.class));
        res.add(new Site(520, "The Guardian", 0xff005689, SiteCountry.UK | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.TheGuardianSections.class, com.lucevent.newsup.data.reader.TheGuardian.class));
        res.add(new Site(525, "The Herald Scotland", 0xffffffff, SiteCountry.UK | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.TheHeraldScotlandSections.class, com.lucevent.newsup.data.reader.TheHeraldScotland.class));

        // American news
        res.add(new Site(600, "CNN", 0xffc20000, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.CNNSections.class, com.lucevent.newsup.data.reader.CNN.class));
        res.add(new Site(605, "The Huffington Post USA", 0xff2c705f, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.HuffingtonPostUSASections.class, com.lucevent.newsup.data.reader.HuffingtonPostInt.class));
        res.add(new Site(610, "USA Today", 0xff009bff, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.USATodaySections.class, com.lucevent.newsup.data.reader.USAToday.class));
        res.add(new Site(615, "The New York Times", 0xffffffff, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.TheNewYorkTimesSections.class, com.lucevent.newsup.data.reader.TheNewYorkTimes.class));
        res.add(new Site(620, "The Bolivar Commercial", 0xffffffff, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.TheBolivarCommercialSections.class, com.lucevent.newsup.data.reader.TheBolivarCommercial.class));

        //Other newspapers or news websites
        res.add(new Site(700, "The Local", 0xfff76e05, SiteCountry.VARIOUS | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.TheLocalSections.class, com.lucevent.newsup.data.reader.TheLocal.class));
        res.add(new Site(715, "The Huffington Post Canada", 0xff2c705f, SiteCountry.CANADA | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.HuffingtonPostCanadaSections.class, com.lucevent.newsup.data.reader.HuffingtonPostInt.class));
        res.add(new Site(720, "The Siberian Times", 0xff0271b9, SiteCountry.RUSSIA | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.SiberianTimesSections.class, com.lucevent.newsup.data.reader.SiberianTimes.class));
        res.add(new Site(725, "The Times of India", 0xff8e1e1d, SiteCountry.INDIA | SiteLanguage.ENGLISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.TheTimesOfIndiaSections.class, com.lucevent.newsup.data.reader.TheTimesOfIndia.class));
        res.add(new Site(730, "La Patilla", 0xff4B8E40, SiteCountry.VENEZUELA | SiteLanguage.SPANISH | SiteCategory.NEWS,
                com.lucevent.newsup.data.section.LaPatillaSections.class, com.lucevent.newsup.data.reader.LaPatilla.class));

        // Technology sites
        res.add(new Site(800, "El Androide Libre", 0xffa3c23e, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.ElAndroideLibreSections.class, com.lucevent.newsup.data.reader.ElAndroideLibre.class));
        res.add(new Site(805, "Digital Trends", 0xff0098d9, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.DigitalTrendsSections.class, com.lucevent.newsup.data.reader.DigitalTrends.class));
        res.add(new Site(810, "Lifehacker", 0xff94b330, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.LifeHackerSections.class, com.lucevent.newsup.data.reader.LifeHacker.class));
        res.add(new Site(815, "Xataka", 0xff212a34, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.XatakaSections.class, com.lucevent.newsup.data.reader.Xataka.class));
        res.add(new Site(820, "TED", 0xffffffff, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.SCIENCE,
                com.lucevent.newsup.data.section.TEDSections.class, com.lucevent.newsup.data.reader.TED.class));
        res.add(new Site(825, "Gizmodo", 0xff9c9c9c, SiteCountry.VARIOUS | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.GizmodoSections.class, com.lucevent.newsup.data.reader.Gizmodo.class));
        res.add(new Site(830, "Android Authority", 0xff8cc234, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.AndroidAuthoritySections.class, com.lucevent.newsup.data.reader.AndroidAuthority.class));
        res.add(new Site(835, "Computer Hoy", 0xff1a1a1a, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.ComputerHoySections.class, com.lucevent.newsup.data.reader.ComputerHoy.class));
        res.add(new Site(840, "Swedroid", 0xff485366, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.SwedroidSections.class, com.lucevent.newsup.data.reader.Swedroid.class));
        res.add(new Site(845, "Hipertextual", 0xff2799d7, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.HipertextualSections.class, com.lucevent.newsup.data.reader.Hipertextual.class));
        res.add(new Site(850, "Mashable", 0xff01aef0, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.MashableSections.class, com.lucevent.newsup.data.reader.Mashable.class));
        res.add(new Site(855, "PC World", 0xff941622, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.PCWorldSections.class, com.lucevent.newsup.data.reader.PCWorld.class));
        res.add(new Site(860, "The Verge", 0xfffa4b2a, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.TheVergeSections.class, com.lucevent.newsup.data.reader.TheVerge.class));
        res.add(new Site(865, "Clipset", 0xff000000, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.ClipsetSections.class, com.lucevent.newsup.data.reader.Clipset.class));
        res.add(new Site(870, "TechCrunch", 0xff1a9711, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.TechCrunchSections.class, com.lucevent.newsup.data.reader.TechCrunch.class));
        res.add(new Site(875, "Andro4all", 0xffffffff, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.Andro4allSections.class, com.lucevent.newsup.data.reader.Andro4all.class));
        res.add(new Site(880, "Topes de Gama", 0xff1B303E, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.TopesDeGamaSections.class, com.lucevent.newsup.data.reader.TopesDeGama.class));
        res.add(new Site(885, "The Geek Hammer", 0xff00A3EB, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.TheGeekHammerSections.class, com.lucevent.newsup.data.reader.TheGeekHammer.class));

        // Blog sites
        res.add(new Site(900, "Medium", 0xffffffff, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.BLOG,
                com.lucevent.newsup.data.section.MediumSections.class, com.lucevent.newsup.data.reader.Medium.class));
        res.add(new Site(905, "Verne", 0xff02b283, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.BLOG,
                com.lucevent.newsup.data.section.VerneSections.class, com.lucevent.newsup.data.reader.Verne.class));
        res.add(new Site(910, "Digital Inspiration", 0xff000000, SiteCountry.INDIA | SiteLanguage.ENGLISH | SiteCategory.TECHNOLOGY,
                com.lucevent.newsup.data.section.DigitalInspirationSections.class, com.lucevent.newsup.data.reader.DigitalInspiration.class));
        res.add(new Site(915, "Google Earth Blog", 0xffffffff, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.BLOG,
                com.lucevent.newsup.data.section.GoogleEarthBlogSections.class, com.lucevent.newsup.data.reader.GoogleEarthBlog.class));

        // Magazines sites (1000)
        res.add(new Site(1010, "Rolling Stone", 0xff1c202b, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.MAGAZINE,
                com.lucevent.newsup.data.section.RollingStoneSections.class, com.lucevent.newsup.data.reader.RollingStone.class));
        res.add(new Site(1015, "People", 0xff20b3e8, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.MAGAZINE,
                com.lucevent.newsup.data.section.PeopleSections.class, com.lucevent.newsup.data.reader.People.class));
        res.add(new Site(1020, "Time", 0xffe60000, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.MAGAZINE,
                com.lucevent.newsup.data.section.TimeSections.class, com.lucevent.newsup.data.reader.Time.class));
        res.add(new Site(1025, "The Atlantic", 0xff030202, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.MAGAZINE,
                com.lucevent.newsup.data.section.TheAtlanticSections.class, com.lucevent.newsup.data.reader.TheAtlantic.class));
        res.add(new Site(1000, "Make:", 0xff4ecbf5, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.MAGAZINE,
                com.lucevent.newsup.data.section.MakeSections.class, com.lucevent.newsup.data.reader.Make.class));
        res.add(new Site(1005, "Discover", 0xff171717, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.MAGAZINE,
                com.lucevent.newsup.data.section.DiscoverSections.class, com.lucevent.newsup.data.reader.Discover.class));
        res.add(new Site(1045, "Digital Camera", 0xffffffff, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.MAGAZINE,
                com.lucevent.newsup.data.section.DigitalCameraSections.class, com.lucevent.newsup.data.reader.DigitalCamera.class));
        res.add(new Site(1060, "Elle", 0xffffffff, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.MAGAZINE,
                com.lucevent.newsup.data.section.ElleSections.class, com.lucevent.newsup.data.reader.Elle.class));
        res.add(new Site(1065, "The Intercept", 0xff000000, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.MAGAZINE,
                com.lucevent.newsup.data.section.TheInterceptSections.class, com.lucevent.newsup.data.reader.TheIntercept.class));

        res.add(new Site(1035, "Dogster", 0xff547a94, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.MAGAZINE,
                com.lucevent.newsup.data.section.DogsterSections.class, com.lucevent.newsup.data.reader.Dogster.class));
        res.add(new Site(1040, "El Jueves", 0xffcb1f1f, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.MAGAZINE,
                com.lucevent.newsup.data.section.ElJuevesSections.class, com.lucevent.newsup.data.reader.ElJueves.class));

        // Astronomy (1200)
        res.add(new Site(1030, "Sky and Telescope", 0xffd92326, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.ASTRONOMY,
                com.lucevent.newsup.data.section.SkyAndTelescopeSections.class, com.lucevent.newsup.data.reader.SkyAndTelescope.class));
        res.add(new Site(1050, "Space News", 0xffffffff, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.ASTRONOMY,
                com.lucevent.newsup.data.section.SpaceNewsSections.class, com.lucevent.newsup.data.reader.SpaceNews.class));
        //       res.add(new Site(1210, "Space.com", 0xff000000, SiteCountry.USA | SiteLanguage.ENGLISH | SiteCategory.ASTRONOMY,
        //               com.lucevent.newsup.data.section.SpaceSections.class, com.lucevent.newsup.data.reader.Space.class));
        res.add(new Site(1215, "Cosmo Noticias", 0xff181818, SiteCountry.CHILE | SiteLanguage.SPANISH | SiteCategory.ASTRONOMY,
                com.lucevent.newsup.data.section.CosmoNoticiasSections.class, com.lucevent.newsup.data.reader.CosmoNoticias.class));
        res.add(new Site(1220, "Universe Today", 0xff6963ac, SiteCountry.CANADA | SiteLanguage.ENGLISH | SiteCategory.ASTRONOMY,
                com.lucevent.newsup.data.section.UniverseTodaySections.class, com.lucevent.newsup.data.reader.UniverseToday.class));

        // Videogames (1300)
        res.add(new Site(1300, "Meristation", 0xffff6a00, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.VIDEOGAMES,
                com.lucevent.newsup.data.section.MeristationSections.class, com.lucevent.newsup.data.reader.Meristation.class));
        res.add(new Site(1305, "Vandal", 0xffffffff, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.VIDEOGAMES,
                com.lucevent.newsup.data.section.VandalSections.class, com.lucevent.newsup.data.reader.Vandal.class));

        // LifeStyle (1400)
        res.add(new Site(1400, "Vice", 0xff000000, SiteCountry.VARIOUS | SiteLanguage.VARIOUS | SiteCategory.LIFESTYLE,
                com.lucevent.newsup.data.section.ViceSections.class, com.lucevent.newsup.data.reader.Vice.class));
        res.add(new Site(1405, "C\u00F3digo Nuevo", 0xff000000, SiteCountry.SPAIN | SiteLanguage.SPANISH | SiteCategory.LIFESTYLE,
                com.lucevent.newsup.data.section.CodigoNuevoSections.class, com.lucevent.newsup.data.reader.CodigoNuevo.class));

        // Science (1500)
        res.add(new Site(1500, "Life Science Sweden", 0xff962D31, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.SCIENCE,
                com.lucevent.newsup.data.section.LifeScienceSwedenSections.class, com.lucevent.newsup.data.reader.LifeScienceSweden.class));

        // Motor (1600)
        res.add(new Site(1600, "Teknikens V\u00E4rld", 0xffEE1D23, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.MOTOR,
                com.lucevent.newsup.data.section.TeknikensVarldSections.class, com.lucevent.newsup.data.reader.TeknikensVarld.class));

        //Coming soon
/*
        res.add(new Site(320, "Goteborgs Posten", 0xff005c9e, SiteCountry.SWEDEN | SiteLanguage.SWEDISH | SiteCategory.NEWS, com.lucevent.newsup.data.section.GoteborgsPostenSections.class, com.lucevent.newsup.data.reader.GoteborgsPosten.class));
        res.add(new Site(XXXX2, "National Geographic", 0xffXXXXX, SiteCountry. | SiteLanguage. | SiteCategory., com.lucevent.newsup.data.section.NationalGeographicSections.class, com.lucevent.newsup.data.reader.NationalGeographic.class));
        res.add(new Site(XXXX3, "Cuore", 0xffXXXXX, SiteCountry. | SiteLanguage. | SiteCategory., com.lucevent.newsup.data.section.CuoreSections.class, com.lucevent.newsup.data.reader.Cuore.class));
        res.add(new Site(XXXX4, "Interviu", 0xffXXXXX, SiteCountry. | SiteLanguage. | SiteCategory., com.lucevent.newsup.data.section.InterviuSections.class, com.lucevent.newsup.data.reader.Interviu.class));
        res.add(new Site(XXXX9, "Motherboard", 0xffXXXXX, SiteCountry. | SiteLanguage. | SiteCategory., com.lucevent.newsup.data.section.MotherboardSections.class, com.lucevent.newsup.data.reader.Motherboard.class));
        res.add(new Site(XXXX0, "Vice News", 0xffXXXXX, SiteCountry. | SiteLanguage. | SiteCategory., com.lucevent.newsup.data.section.ViceNewsSections.class, com.lucevent.newsup.data.reader.ViceNews.class));
        res.add(new Site(XXXX1, "Vogue España", 0xffXXXXX, SiteCountry. | SiteLanguage. | SiteCategory., com.lucevent.newsup.data.section.VogueESSections.class, com.lucevent.newsup.data.reader.VogueES.class));
        res.add(new Site(XXXX2, "IGN España", 0xffXXXXX, SiteCountry. | SiteLanguage. | SiteCategory., com.lucevent.newsup.data.section.IGNESSections.class, com.lucevent.newsup.data.reader.IGNES.class));
        res.add(new Site(XXXX6, "Chicago Sun-Times", 0xffXXXXX, SiteCountry. | SiteLanguage. | SiteCategory., com.lucevent.newsup.data.section.ChicagoSunTimesSections.class, com.lucevent.newsup.data.reader.ChicagoSunTimes.class));
        res.add(new Site(XXXX7, "Wwwhat's New", 0xffXXXXX, SiteCountry. | SiteLanguage. | SiteCategory., com.lucevent.newsup.data.section.WwwhatsNewSections.class, com.lucevent.newsup.data.reader.WwwhatsNew.class));
        res.add(new Site(XXXX8, "ElImparcial", 0xffXXXXX, SiteCountry. | SiteLanguage. | SiteCategory., com.lucevent.newsup.data.section.ElImparcialSections.class, com.lucevent.newsup.data.reader.ElImparcial.class));
        res.add(new Site(XXXX9, "Astronomy Now", 0xffXXXXX, SiteCountry. | SiteLanguage. | SiteCategory., com.lucevent.newsup.data.section.AstronomyNowSections.class, com.lucevent.newsup.data.reader.AstronomyNow.class));
        res.add(new Site(XXXX1, "Cinco Días", 0xffXXXXX, SiteCountry. | SiteLanguage. | SiteCategory., com.lucevent.newsup.data.section.CincoDiasSections.class, com.lucevent.newsup.data.reader.CincoDias.class));
        res.add(new Site(XXXX2, "theCHIVE", 0xffXXXXX, SiteCountry. | SiteLanguage. | SiteCategory., com.lucevent.newsup.data.section.theCHIVESections.class, com.lucevent.newsup.data.reader.theCHIVE.class));
        res.add(new Site(XXXX4, "ComingSoon", 0xffXXXXX, SiteCountry. | SiteLanguage. | SiteCategory., com.lucevent.newsup.data.section.ComingSoonSections.class, com.lucevent.newsup.data.reader.ComingSoon.class));
*/
        return res;
    }

    public Site getSiteByCode(int code)
    {
        for (Site site : this)
            if (site.code == code)
                return site;

        return null;
    }

}