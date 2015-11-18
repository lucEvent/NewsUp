package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_SportNewsReader extends BE_NewsReader {

    public BE_SportNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Últimas noticias", "http://www.sport.es/es/rss/last_news/rss.xml"));

        SECTIONS.add(new BE_Section("Fútbol", "http://www.sport.es/es/rss/futbol/rss.xml"));

        SECTIONS.add(new BE_Section("Barça", "http://www.sport.es/es/rss/barca/rss.xml"));
        SECTIONS.add(new BE_Section("Madrid", "http://www.sport.es/es/rss/real-madrid/rss.xml"));
        SECTIONS.add(new BE_Section("Espanyol", "http://www.sport.es/es/rss/espanyol/rss.xml"));
        SECTIONS.add(new BE_Section("Liga bbva", "http://www.sport.es/es/rss/liga-bbva/rss.xml"));
        SECTIONS.add(new BE_Section("Liga adelante", "http://www.sport.es/es/rss/liga-adelante/rss.xml"));
        SECTIONS.add(new BE_Section("Champions league", "http://www.sport.es/es/rss/champions/rss.xml"));
        SECTIONS.add(new BE_Section("Europa league", "http://www.sport.es/es/rss/europa-league/rss.xml"));
        SECTIONS.add(new BE_Section("Copa del rey", "http://www.sport.es/es/rss/copa-del-rey/rss.xml"));
        SECTIONS.add(new BE_Section("Selección", "http://www.sport.es/es/rss/seleccion/rss.xml"));
        SECTIONS.add(new BE_Section("Futbol català", "http://www.sport.es/es/rss/futbol-catala/rss.xml"));
        SECTIONS.add(new BE_Section("Fútbol base", "http://www.sport.es/es/rss/futbol-base/rss.xml"));
        SECTIONS.add(new BE_Section("Fútbol américa", "http://www.sport.es/es/rss/futbol-america/rss.xml"));
        SECTIONS.add(new BE_Section("Fútbol internacional", "http://www.sport.es/es/rss/futbol-internacional/rss.xml"));


        SECTIONS.add(new BE_Section("Basket", "http://www.sport.es/es/rss/baloncesto/rss.xml"));
        SECTIONS.add(new BE_Section("Liga endesa", "http://www.sport.es/es/rss/acb/rss.xml"));
        SECTIONS.add(new BE_Section("Euroliga", "http://www.sport.es/es/rss/euroliga/rss.xml"));
        SECTIONS.add(new BE_Section("NBA", "http://www.sport.es/es/rss/nba/rss.xml"));
        SECTIONS.add(new BE_Section("Mundial baloncesto", "http://www.sport.es/es/rss/mundial-de-baloncesto/rss.xml"));

        SECTIONS.add(new BE_Section("Internacional", null));
        SECTIONS.add(new BE_Section("Inglaterra", "http://www.sport.es/es/rss/inglaterra/rss.xml"));
        SECTIONS.add(new BE_Section("Francia", "http://www.sport.es/es/rss/francia/rss.xml"));
        SECTIONS.add(new BE_Section("Italia", "http://www.sport.es/es/rss/italia/rss.xml"));
        SECTIONS.add(new BE_Section("Alemania", "http://www.sport.es/es/rss/alemania/rss.xml"));
        SECTIONS.add(new BE_Section("Resto del mundo", "http://www.sport.es/es/rss/resto-del-mundo/rss.xml"));

        SECTIONS.add(new BE_Section("Motor", "http://www.sport.es/es/rss/motor/rss.xml"));
        SECTIONS.add(new BE_Section("Fórmula 1", "http://www.sport.es/es/rss/formula1/rss.xml"));
        SECTIONS.add(new BE_Section("Motociclismo", "http://www.sport.es/es/rss/motociclismo/rss.xml"));
        SECTIONS.add(new BE_Section("Más motor", "http://www.sport.es/es/rss/mas-motor/rss.xml"));
        SECTIONS.add(new BE_Section("Mundo motor", "http://www.sport.es/es/rss/mundo-motor/rss.xml"));

        SECTIONS.add(new BE_Section("Tenis", "http://www.sport.es/es/rss/tenis/rss.xml"));
        SECTIONS.add(new BE_Section("Atletismo", "http://www.sport.es/es/rss/atletismo/rss.xml"));
        SECTIONS.add(new BE_Section("Ciclismo", "http://www.sport.es/es/rss/ciclismo/rss.xml"));
        SECTIONS.add(new BE_Section("Hockey", "http://www.sport.es/es/rss/hockey/rss.xml"));
        SECTIONS.add(new BE_Section("Fútbol sala", "http://www.sport.es/es/rss/futbol-sala/rss.xml"));
        SECTIONS.add(new BE_Section("Balonmano", "http://www.sport.es/es/rss/balonmano/rss.xml"));
        SECTIONS.add(new BE_Section("Pàdel", "http://www.sport.es/es/rss/padel/rss.xml"));
        SECTIONS.add(new BE_Section("Golf", "http://www.sport.es/es/rss/golf/rss.xml"));
        SECTIONS.add(new BE_Section("Deporte extremo", "http://www.sport.es/es/rss/deporte-extremo/rss.xml"));
        SECTIONS.add(new BE_Section("Otros deportes", "http://www.sport.es/es/rss/mas-deportes/rss.xml"));

        SECTIONS.add(new BE_Section("Opinión", "http://www.sport.es/es/rss/opinion/rss.xml"));
        SECTIONS.add(new BE_Section("Veteranos", "http://www.sport.es/es/rss/veteranos/rss.xml"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).getElementsByTag("p").text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements img = doc.select(".sp-img,.sp-video").select("img");

        org.jsoup.select.Elements content = doc.select(".cuerpo-noticia");
        content.select(".sp-autor").remove();

        if (!content.isEmpty()) {
            news.content = img.outerHtml() + content.html();
        } else {
            content = doc.select(".cuerpo-opinion");
            content.select(".firma").remove();

            if (!content.isEmpty()) {
                news.content = content.html();
            }
        }
    }

}