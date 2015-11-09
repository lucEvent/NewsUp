package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

import org.jsoup.Jsoup;

public class SportNewsReader extends NewsReader {

    public SportNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Últimas noticias", 0, "http://www.sport.es/es/rss/last_news/rss.xml"));

        SECTIONS.add(new Section("Fútbol", 0, "http://www.sport.es/es/rss/futbol/rss.xml"));

        SECTIONS.add(new Section("Barça", 1, "http://www.sport.es/es/rss/barca/rss.xml"));
        SECTIONS.add(new Section("Madrid", 1, "http://www.sport.es/es/rss/real-madrid/rss.xml"));
        SECTIONS.add(new Section("Espanyol", 1, "http://www.sport.es/es/rss/espanyol/rss.xml"));
        SECTIONS.add(new Section("Liga bbva", 1, "http://www.sport.es/es/rss/liga-bbva/rss.xml"));
        SECTIONS.add(new Section("Liga adelante", 1, "http://www.sport.es/es/rss/liga-adelante/rss.xml"));
        SECTIONS.add(new Section("Champions league", 1, "http://www.sport.es/es/rss/champions/rss.xml"));
        SECTIONS.add(new Section("Europa league", 1, "http://www.sport.es/es/rss/europa-league/rss.xml"));
        SECTIONS.add(new Section("Copa del rey", 1, "http://www.sport.es/es/rss/copa-del-rey/rss.xml"));
        SECTIONS.add(new Section("Selección", 1, "http://www.sport.es/es/rss/seleccion/rss.xml"));
        SECTIONS.add(new Section("Futbol català", 1, "http://www.sport.es/es/rss/futbol-catala/rss.xml"));
        SECTIONS.add(new Section("Fútbol base", 1, "http://www.sport.es/es/rss/futbol-base/rss.xml"));
        SECTIONS.add(new Section("Fútbol américa", 1, "http://www.sport.es/es/rss/futbol-america/rss.xml"));
        SECTIONS.add(new Section("Fútbol internacional", 1, "http://www.sport.es/es/rss/futbol-internacional/rss.xml"));


        SECTIONS.add(new Section("Basket", 0, "http://www.sport.es/es/rss/baloncesto/rss.xml"));
        SECTIONS.add(new Section("Liga endesa", 1, "http://www.sport.es/es/rss/acb/rss.xml"));
        SECTIONS.add(new Section("Euroliga", 1, "http://www.sport.es/es/rss/euroliga/rss.xml"));
        SECTIONS.add(new Section("NBA", 1, "http://www.sport.es/es/rss/nba/rss.xml"));
        SECTIONS.add(new Section("Mundial baloncesto", 1, "http://www.sport.es/es/rss/mundial-de-baloncesto/rss.xml"));

        SECTIONS.add(new Section("Internacional", 0, null));
        SECTIONS.add(new Section("Inglaterra", 1, "http://www.sport.es/es/rss/inglaterra/rss.xml"));
        SECTIONS.add(new Section("Francia", 1, "http://www.sport.es/es/rss/francia/rss.xml"));
        SECTIONS.add(new Section("Italia", 1, "http://www.sport.es/es/rss/italia/rss.xml"));
        SECTIONS.add(new Section("Alemania", 1, "http://www.sport.es/es/rss/alemania/rss.xml"));
        SECTIONS.add(new Section("Resto del mundo", 1, "http://www.sport.es/es/rss/resto-del-mundo/rss.xml"));

        SECTIONS.add(new Section("Motor", 0, "http://www.sport.es/es/rss/motor/rss.xml"));
        SECTIONS.add(new Section("Fórmula 1", 1, "http://www.sport.es/es/rss/formula1/rss.xml"));
        SECTIONS.add(new Section("Motociclismo", 1, "http://www.sport.es/es/rss/motociclismo/rss.xml"));
        SECTIONS.add(new Section("Más motor", 1, "http://www.sport.es/es/rss/mas-motor/rss.xml"));
        SECTIONS.add(new Section("Mundo motor", 1, "http://www.sport.es/es/rss/mundo-motor/rss.xml"));

        SECTIONS.add(new Section("Tenis", 0, "http://www.sport.es/es/rss/tenis/rss.xml"));
        SECTIONS.add(new Section("Atletismo", 0, "http://www.sport.es/es/rss/atletismo/rss.xml"));
        SECTIONS.add(new Section("Ciclismo", 0, "http://www.sport.es/es/rss/ciclismo/rss.xml"));
        SECTIONS.add(new Section("Hockey", 0, "http://www.sport.es/es/rss/hockey/rss.xml"));
        SECTIONS.add(new Section("Fútbol sala", 0, "http://www.sport.es/es/rss/futbol-sala/rss.xml"));
        SECTIONS.add(new Section("Balonmano", 0, "http://www.sport.es/es/rss/balonmano/rss.xml"));
        SECTIONS.add(new Section("Pàdel", 0, "http://www.sport.es/es/rss/padel/rss.xml"));
        SECTIONS.add(new Section("Golf", 0, "http://www.sport.es/es/rss/golf/rss.xml"));
        SECTIONS.add(new Section("Deporte extremo", 0, "http://www.sport.es/es/rss/deporte-extremo/rss.xml"));
        SECTIONS.add(new Section("Otros deportes", 0, "http://www.sport.es/es/rss/mas-deportes/rss.xml"));

        SECTIONS.add(new Section("Opinión", 0, "http://www.sport.es/es/rss/opinion/rss.xml"));
        SECTIONS.add(new Section("Veteranos", 0, "http://www.sport.es/es/rss/veteranos/rss.xml"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = Jsoup.parse(news.description).getElementsByTag("p").text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
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