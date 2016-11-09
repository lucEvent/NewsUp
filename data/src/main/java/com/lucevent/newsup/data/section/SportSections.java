package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class SportSections extends Sections {

    public SportSections()
    {
        super();

        add(new Section("\u00DAltimas noticias", "http://www.sport.es/es/rss/last_news/rss.xml", 0));

        add(new Section("F\u00FAtbol", "http://www.sport.es/es/rss/futbol/rss.xml", 0));
        add(new Section("Bar\u00E7a", "http://www.sport.es/es/rss/barca/rss.xml", 1));
        add(new Section("Madrid", "http://www.sport.es/es/rss/real-madrid/rss.xml", 1));
        add(new Section("Espanyol", "http://www.sport.es/es/rss/espanyol/rss.xml", 1));
        add(new Section("LaLiga Santander", "http://www.sport.es/es/rss/liga-bbva/rss.xml", 1));
        add(new Section("LaLiga 1|2|3", "http://www.sport.es/es/rss/liga-adelante/rss.xml", 1));
        add(new Section("Champions league", "http://www.sport.es/es/rss/champions/rss.xml", 1));
        add(new Section("Europa league", "http://www.sport.es/es/rss/europa-league/rss.xml", 1));
        add(new Section("Copa del Rey", "http://www.sport.es/es/rss/copa-del-rey/rss.xml", 1));
        add(new Section("Selecci\u00F3n", "http://www.sport.es/es/rss/seleccion/rss.xml", 1));
        add(new Section("F\u00FAtbol catal\u00E0", "http://www.sport.es/es/rss/futbol-catala/rss.xml", 1));
        add(new Section("F\u00FAtbol base", "http://www.sport.es/es/rss/futbol-base/rss.xml", 1));
        add(new Section("F\u00FAtbol am\u00E9rica", "http://www.sport.es/es/rss/futbol-america/rss.xml", 1));
        add(new Section("F\u00FAtbol internacional", "http://www.sport.es/es/rss/futbol-internacional/rss.xml", 1));

        add(new Section("Basket", "http://www.sport.es/es/rss/baloncesto/rss.xml", 0));
        add(new Section("Liga Endesa", "http://www.sport.es/es/rss/acb/rss.xml", 1));
        add(new Section("Euroliga", "http://www.sport.es/es/rss/euroliga/rss.xml", 1));
        add(new Section("NBA", "http://www.sport.es/es/rss/nba/rss.xml", 1));

        add(new Section("Internacional", null, -1));
        add(new Section("Inglaterra", "http://www.sport.es/es/rss/inglaterra/rss.xml", 1));
        add(new Section("Francia", "http://www.sport.es/es/rss/francia/rss.xml", 1));
        add(new Section("Italia", "http://www.sport.es/es/rss/italia/rss.xml", 1));
        add(new Section("Alemania", "http://www.sport.es/es/rss/alemania/rss.xml", 1));
        add(new Section("Resto del mundo", "http://www.sport.es/es/rss/resto-del-mundo/rss.xml", 1));

        add(new Section("Motor", "http://www.sport.es/es/rss/motor/rss.xml", 0));
        add(new Section("F\u00F3rmula 1", "http://www.sport.es/es/rss/formula1/rss.xml", 1));
        add(new Section("Motociclismo", "http://www.sport.es/es/rss/motociclismo/rss.xml", 1));
        add(new Section("M\u00E1s motor", "http://www.sport.es/es/rss/mas-motor/rss.xml", 1));
        add(new Section("Mundo motor", "http://www.sport.es/es/rss/mundo-motor/rss.xml", 1));

        add(new Section("Tenis", "http://www.sport.es/es/rss/tenis/rss.xml", 0));
        add(new Section("Atletismo", "http://www.sport.es/es/rss/atletismo/rss.xml", 0));
        add(new Section("Ciclismo", "http://www.sport.es/es/rss/ciclismo/rss.xml", 0));
        add(new Section("Hockey", "http://www.sport.es/es/rss/hockey/rss.xml", 0));
        add(new Section("F\u00FAtbol sala", "http://www.sport.es/es/rss/futbol-sala/rss.xml", 0));
        add(new Section("Balonmano", "http://www.sport.es/es/rss/balonmano/rss.xml", 0));
        add(new Section("P\u00E1del", "http://www.sport.es/es/rss/padel/rss.xml", 0));
        add(new Section("Golf", "http://www.sport.es/es/rss/golf/rss.xml", 0));
        add(new Section("Deporte extremo", "http://www.sport.es/es/rss/deporte-extremo/rss.xml", 0));
        add(new Section("Otros deportes", "http://www.sport.es/es/rss/mas-deportes/rss.xml", 0));

        add(new Section("Opini\u00F3n", "http://www.sport.es/es/rss/opinion/rss.xml", 0));
        add(new Section("Veteranos", "http://www.sport.es/es/rss/veteranos/rss.xml", 0));

    }

}