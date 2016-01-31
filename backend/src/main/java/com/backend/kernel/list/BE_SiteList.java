package com.backend.kernel.list;

import com.backend.kernel.BE_Site;

import java.util.ArrayList;

public class BE_SiteList extends ArrayList<BE_Site> {

    public BE_SiteList() {
        super();

        // Spanish sites
        add(new BE_Site(100, "El Pais", new com.backend.net.BE_ElPaisNewsReader()));
        add(new BE_Site(105, "20 Minutos", new com.backend.net.BE_20MinutosNewsReader()));
        add(new BE_Site(110, "El Mundo", new com.backend.net.BE_ElMundoNewsReader()));
        add(new BE_Site(115, "As", new com.backend.net.BE_AsNewsReader()));
        add(new BE_Site(120, "Marca", new com.backend.net.BE_MarcaNewsReader()));
        add(new BE_Site(125, "El Confidencial", new com.backend.net.BE_ElConfidencialNewsReader()));
        add(new BE_Site(130, "El Diario", new com.backend.net.BE_ElDiarioNewsReader()));
        add(new BE_Site(135, "La Razón", new com.backend.net.BE_LaRazonNewsReader()));
        add(new BE_Site(140, "Huffington Post", new com.backend.net.BE_HuffingtonPostSpainNewsReader()));
        add(new BE_Site(145, "Europa press", new com.backend.net.BE_EuropaPressNewsReader()));
        add(new BE_Site(150, "Diario Córdoba", new com.backend.net.BE_DiarioCordobaNewsReader()));

        // Catalan sites
        add(new BE_Site(200, "El Periódico (Cat)", new com.backend.net.BE_ElPeriodicoCaNewsReader()));
        add(new BE_Site(205, "El Periódico (Esp)", new com.backend.net.BE_ElPeriodicoEsNewsReader()));
        add(new BE_Site(210, "La Vanguardia", new com.backend.net.BE_LaVanguardiaNewsReader()));
        add(new BE_Site(215, "Sport", new com.backend.net.BE_SportNewsReader()));
        add(new BE_Site(220, "Mundo Deportivo", new com.backend.net.BE_MundoDeportivoNewsReader()));

        // Swedish sites
        add(new BE_Site(300, "Aftonbladet", new com.backend.net.BE_AftonbladetNewsReader()));
        add(new BE_Site(305, "Expressen", new com.backend.net.BE_ExpressenNewsReader()));
        add(new BE_Site(310, "Dagens Nyheter", new com.backend.net.BE_DagensNyheterNewsReader()));
        add(new BE_Site(315, "Svenska Dagbladet", new com.backend.net.BE_SvenskaDagbladetNewsReader()));
        add(new BE_Site(320, "Goteborgs Posten", new com.backend.net.BE_GoteborgsPostenNewsReader()));
        add(new BE_Site(325, "Fria Tider", new com.backend.net.BE_FriaTiderNewsReader()));
        add(new BE_Site(330, "Metro", new com.backend.net.BE_MetroNewsReader()));

        // Finnish sites
        add(new BE_Site(400, "Helsinki times", new com.backend.net.BE_HelsinkiTimesNewsReader()));
        add(new BE_Site(405, "Helsingin Sanomat", new com.backend.net.BE_HelsinkiSanomatNewsReader()));
        add(new BE_Site(410, "Iltalehti", new com.backend.net.BE_IltalehtiNewsReader()));
        add(new BE_Site(415, "Yle", new com.backend.net.BE_YleNewsReader()));

        // British sites
        add(new BE_Site(500, "BBC", new com.backend.net.BE_BCCNewsReader()));
        add(new BE_Site(505, "The Telegraph", new com.backend.net.BE_TheTelegraphNewsReader()));
        add(new BE_Site(510, "The Huffington Post", new com.backend.net.BE_HuffingtonPostUKNewsReader()));

        // American sites
        add(new BE_Site(600, "CNN", new com.backend.net.BE_CNNNewsReader()));
//          add(new BE_Site(605, "The Huffington Post", new com.backend.net.BE_HuffingtonPostUSANewsReader()));

        // International sites
        add(new BE_Site(700, "The Local", new com.backend.net.BE_TheLocalNewsReader()));
//          add(new BE_Site(705, "The Huffington Post (Arabic)", new com.backend.net.BE_HuffingtonPostArabicNewsReader()));
//          add(new BE_Site(710, "The Huffington Post (Australia)", new com.backend.net.BE_HuffingtonPostAustraliaNewsReader()));
//          add(new BE_Site(715, "The Huffington Post (Brasil)", new com.backend.net.BE_HuffingtonPostBrasilNewsReader()));
//          add(new BE_Site(720, "The Huffington Post (Canada)", new com.backend.net.BE_HuffingtonPostCanadaNewsReader()));

        // Tecnology sites
        add(new BE_Site(800, "El Androide Libre", new com.backend.net.BE_ElAndroideLibreNewsReader()));
        add(new BE_Site(805, "Digital Trends", new com.backend.net.BE_DigitalTrendsNewsReader()));
        add(new BE_Site(810, "Lifehacker", new com.backend.net.BE_LifeHackerNewsReader()));
        add(new BE_Site(815, "Xataka", new com.backend.net.BE_XatakaNewsReader()));
        add(new BE_Site(820, "TED", new com.backend.net.BE_TEDNewsReader()));
        add(new BE_Site(825, "Gizmodo", new com.backend.net.BE_GizmodoNewsReader()));
        add(new BE_Site(830, "Android Authority", new com.backend.net.BE_AndroidAuthorityNewsReader()));
        add(new BE_Site(835, "Computer Hoy", new com.backend.net.BE_ComputerHoyNewsReader()));
        add(new BE_Site(840, "Swedroid", new com.backend.net.BE_SwedroidNewsReader()));

        // Blog sites
        add(new BE_Site(900, "Medium", new com.backend.net.BE_MediumNewsReader()));

        // Magazines sites
        add(new BE_Site(1000, "Make", new com.backend.net.BE_MakeNewsReader()));
        add(new BE_Site(1005, "Discover", new com.backend.net.BE_DiscoverNewsReader()));
        add(new BE_Site(1010, "Rolling Stone", new com.backend.net.BE_RollingStoneNewsReader()));
        add(new BE_Site(1015, "People", new com.backend.net.BE_PeopleNewsReader()));
        add(new BE_Site(1020, "Time", new com.backend.net.BE_TimeNewsReader()));
        add(new BE_Site(1025, "The Atlantic", new com.backend.net.BE_TheAtlanticNewsReader()));
        add(new BE_Site(1030, "Sky and Telescope", new com.backend.net.BE_SkyAndTelescopeNewsReader()));
        add(new BE_Site(1035, "Dogster", new com.backend.net.BE_DogsterNewsReader()));
        add(new BE_Site(1040, "El Jueves", new com.backend.net.BE_ElJuevesNewsReader()));
//          add(new BE_Site(1045, "National Geographic", new com.backend.net.BE_NationalGeographicNewsReader()));
        add(new BE_Site(1050, "Space News", new com.backend.net.BE_SpaceNewsNewsReader()));
//          add(new BE_Site(1055, "Space", new com.backend.net.BE_SpaceNewsReader()));
//          add(new BE_Site(1060, "The Bark", new com.backend.net.BE_TheBarkNewsReader()));

    }

    public BE_Site getSiteByCode(int code) {
        for (BE_Site site : this) {
            if (site.code == code) {
                return site;
            }
        }
        return null;
    }

}
