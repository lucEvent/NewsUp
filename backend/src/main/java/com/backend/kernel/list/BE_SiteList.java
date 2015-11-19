package com.backend.kernel.list;

import com.backend.kernel.BE_Site;

import java.util.ArrayList;

public class BE_SiteList extends ArrayList<BE_Site> {

    public BE_SiteList() {
        super();

        int code = 0;

        // Spanish sites
        add(new BE_Site(code++, "**El Pais", new com.backend.net.BE_ElPaisNewsReader()));
        add(new BE_Site(code++, "**20 Minutos", new com.backend.net.BE_20MinutosNewsReader()));
        add(new BE_Site(code++, "**El Mundo", new com.backend.net.BE_ElMundoNewsReader()));
        add(new BE_Site(code++, "**As", new com.backend.net.BE_AsNewsReader()));
        add(new BE_Site(code++, "**Marca", new com.backend.net.BE_MarcaNewsReader()));
        add(new BE_Site(code++, "**El Confidencial", new com.backend.net.BE_ElConfidencialNewsReader()));
        add(new BE_Site(code++, "**El Diario", new com.backend.net.BE_ElDiarioNewsReader()));
        add(new BE_Site(code++, "**La Razón", new com.backend.net.BE_LaRazonNewsReader()));
        add(new BE_Site(code++, "**Huffington Post", new com.backend.net.BE_HuffingtonPostSpainNewsReader()));

        // Catalan sites
        add(new BE_Site(code++, "**El Periódico (Cat)", new com.backend.net.BE_ElPeriodicoCaNewsReader()));
        add(new BE_Site(code++, "**El Periódico (Esp)", new com.backend.net.BE_ElPeriodicoEsNewsReader()));
        add(new BE_Site(code++, "**La Vanguardia", new com.backend.net.BE_LaVanguardiaNewsReader()));
        add(new BE_Site(code++, "**Sport", new com.backend.net.BE_SportNewsReader()));
        add(new BE_Site(code++, "**Mundo Deportivo", new com.backend.net.BE_MundoDeportivoNewsReader()));

        // Swedish sites
        add(new BE_Site(code++, "**Aftonbladet", new com.backend.net.BE_AftonbladetNewsReader()));
        add(new BE_Site(code++, "**Expressen", new com.backend.net.BE_ExpressenNewsReader()));
        add(new BE_Site(code++, "**Dagens Nyheter", new com.backend.net.BE_DagensNyheterNewsReader()));
        add(new BE_Site(code++, "**Svenska Dagbladet", new com.backend.net.BE_SvenskaDagbladetNewsReader()));
        add(new BE_Site(code++, "**Goteborgs Posten", new com.backend.net.BE_GoteborgsPostenNewsReader()));
        add(new BE_Site(code++, "**Fria Tider", new com.backend.net.BE_FriaTiderNewsReader()));
        add(new BE_Site(code++, "**Metro", new com.backend.net.BE_MetroNewsReader()));

        // Finnish sites
        add(new BE_Site(code++, "**Helsinki times", new com.backend.net.BE_HelsinkiTimesNewsReader()));
        add(new BE_Site(code++, "**Helsingin Sanomat", new com.backend.net.BE_HelsinkiSanomatNewsReader()));
        add(new BE_Site(code++, "**Iltalehti", new com.backend.net.BE_IltalehtiNewsReader()));
        add(new BE_Site(code++, "**Yle", new com.backend.net.BE_YleNewsReader()));

        // British sites
        add(new BE_Site(code++, "**BBC", new com.backend.net.BE_BCCNewsReader()));

        // American sites
        add(new BE_Site(code++, "CNN", new com.backend.net.BE_CNNNewsReader()));

        // International sites
        add(new BE_Site(code++, "**The Local", new com.backend.net.BE_TheLocalNewsReader()));

        // Tecnology sites
        add(new BE_Site(code++, "**El Androide Libre", new com.backend.net.BE_ElAndroideLibreNewsReader()));
        add(new BE_Site(code++, "**Digital Trends", new com.backend.net.BE_DigitalTrendsNewsReader()));
        add(new BE_Site(code++, "**Lifehacker", new com.backend.net.BE_LifeHackerNewsReader()));
        add(new BE_Site(code++, "**Xataka", new com.backend.net.BE_XatakaNewsReader()));
        add(new BE_Site(code++, "TED", new com.backend.net.BE_TEDNewsReader()));
        add(new BE_Site(code++, "Gizmodo", new com.backend.net.BE_GizmodoNewsReader()));
        add(new BE_Site(code++, "**Android Authority", new com.backend.net.BE_AndroidAuthorityNewsReader()));

        // Blog sites
        add(new BE_Site(code++, "Medium", new com.backend.net.BE_MediumNewsReader()));

        // Magazines sites
        add(new BE_Site(code++, "**Make", new com.backend.net.BE_MakeNewsReader()));
        add(new BE_Site(code++, "**Discover", new com.backend.net.BE_DiscoverNewsReader()));
        add(new BE_Site(code++, "**Rolling Stone", new com.backend.net.BE_RollingStoneNewsReader()));
        add(new BE_Site(code++, "**People", new com.backend.net.BE_PeopleNewsReader()));
        add(new BE_Site(code++, "**Time", new com.backend.net.BE_TimeNewsReader()));
        add(new BE_Site(code++, "**The Atlantic", new com.backend.net.BE_TheAtlanticNewsReader()));
        add(new BE_Site(code++, "**Sky and Telescope", new com.backend.net.BE_SkyAndTelescopeNewsReader()));
        add(new BE_Site(code++, "**Dogster", new com.backend.net.BE_DogsterNewsReader()));

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
