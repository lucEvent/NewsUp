package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.kernel.list.Tags;

import org.jsoup.Jsoup;

public class SportNewsReader extends NewsReader {

    public SportNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("ÚLTIMAS NOTICIAS", 0, "http://www.sport.es/es/rss/last_news/rss.xml"));

        SECTIONS.add(new Section("FÚTBOL", 0, "http://www.sport.es/es/rss/futbol/rss.xml"));

        SECTIONS.add(new Section("BARÇA", -1, "http://www.sport.es/es/rss/barca/rss.xml"));
        SECTIONS.add(new Section("MADRID", -1, "http://www.sport.es/es/rss/real-madrid/rss.xml"));
        SECTIONS.add(new Section("ESPANYOL", -1, "http://www.sport.es/es/rss/espanyol/rss.xml"));
        SECTIONS.add(new Section("LIGA BBVA", -1, "http://www.sport.es/es/rss/liga-bbva/rss.xml"));
        SECTIONS.add(new Section("LIGA ADELANTE", -1, "http://www.sport.es/es/rss/liga-adelante/rss.xml"));
        SECTIONS.add(new Section("CHAMPIONS LEAGUE", -1, "http://www.sport.es/es/rss/champions/rss.xml"));
        SECTIONS.add(new Section("EUROPA LEAGUE", -1, "http://www.sport.es/es/rss/europa-league/rss.xml"));
        SECTIONS.add(new Section("COPA DEL REY", -1, "http://www.sport.es/es/rss/copa-del-rey/rss.xml"));
        SECTIONS.add(new Section("SELECCIÓN", -1, "http://www.sport.es/es/rss/seleccion/rss.xml"));
        SECTIONS.add(new Section("FUTBOL CATALÀ", -1, "http://www.sport.es/es/rss/futbol-catala/rss.xml"));
        SECTIONS.add(new Section("FÚTBOL BASE", -1, "http://www.sport.es/es/rss/futbol-base/rss.xml"));
        SECTIONS.add(new Section("FÚTBOL AMÉRICA", -1, "http://www.sport.es/es/rss/futbol-america/rss.xml"));
        SECTIONS.add(new Section("FÚTBOL INTERNACIONAL", -1, "http://www.sport.es/es/rss/futbol-internacional/rss.xml"));


        SECTIONS.add(new Section("BASKET", 0, "http://www.sport.es/es/rss/baloncesto/rss.xml"));
        SECTIONS.add(new Section("LIGA ENDESA", -1, "http://www.sport.es/es/rss/acb/rss.xml"));
        SECTIONS.add(new Section("EUROLIGA", -1, "http://www.sport.es/es/rss/euroliga/rss.xml"));
        SECTIONS.add(new Section("NBA", -1, "http://www.sport.es/es/rss/nba/rss.xml"));
        SECTIONS.add(new Section("MUNDIAL BALONCESTO", -1, "http://www.sport.es/es/rss/mundial-de-baloncesto/rss.xml"));

        SECTIONS.add(new Section("INTERNACIONAL", 0, null));
        SECTIONS.add(new Section("INGLATERRA", -1, "http://www.sport.es/es/rss/inglaterra/rss.xml"));
        SECTIONS.add(new Section("FRANCIA", -1, "http://www.sport.es/es/rss/francia/rss.xml"));
        SECTIONS.add(new Section("ITALIA", -1, "http://www.sport.es/es/rss/italia/rss.xml"));
        SECTIONS.add(new Section("ALEMANIA", -1, "http://www.sport.es/es/rss/alemania/rss.xml"));
        SECTIONS.add(new Section("RESTO DEL MUNDO", -1, "http://www.sport.es/es/rss/resto-del-mundo/rss.xml"));

        SECTIONS.add(new Section("MOTOR", 0, "http://www.sport.es/es/rss/motor/rss.xml"));
        SECTIONS.add(new Section("FÓRMULA 1", -1, "http://www.sport.es/es/rss/formula1/rss.xml"));
        SECTIONS.add(new Section("MOTOCICLISMO", -1, "http://www.sport.es/es/rss/motociclismo/rss.xml"));
        SECTIONS.add(new Section("MÁS MOTOR", -1, "http://www.sport.es/es/rss/mas-motor/rss.xml"));
        SECTIONS.add(new Section("MUNDO MOTOR", -1, "http://www.sport.es/es/rss/mundo-motor/rss.xml"));

        SECTIONS.add(new Section("TENIS", 0, "http://www.sport.es/es/rss/tenis/rss.xml"));
        SECTIONS.add(new Section("ATLETISMO", 0, "http://www.sport.es/es/rss/atletismo/rss.xml"));
        SECTIONS.add(new Section("CICLISMO", 0, "http://www.sport.es/es/rss/ciclismo/rss.xml"));
        SECTIONS.add(new Section("HOCKEY", 0, "http://www.sport.es/es/rss/hockey/rss.xml"));
        SECTIONS.add(new Section("FÚTBOL SALA", 0, "http://www.sport.es/es/rss/futbol-sala/rss.xml"));
        SECTIONS.add(new Section("BALONMANO", 0, "http://www.sport.es/es/rss/balonmano/rss.xml"));
        SECTIONS.add(new Section("PÀDEL", 0, "http://www.sport.es/es/rss/padel/rss.xml"));
        SECTIONS.add(new Section("GOLF", 0, "http://www.sport.es/es/rss/golf/rss.xml"));
        SECTIONS.add(new Section("DEPORTE EXTREMO", 0, "http://www.sport.es/es/rss/deporte-extremo/rss.xml"));
        SECTIONS.add(new Section("+ DEPORTES", 0, "http://www.sport.es/es/rss/mas-deportes/rss.xml"));

        SECTIONS.add(new Section("OPINIÓN", 0, "http://www.sport.es/es/rss/opinion/rss.xml"));
        SECTIONS.add(new Section("VETERANOS", 0, "http://www.sport.es/es/rss/veteranos/rss.xml"));

    }

    protected News getNewsLastFilter(String title, String link, String description, String date, Tags categories) {
        description = Jsoup.parse(description).getElementsByTag("p").text();
        return new News(title, link, description, date, categories);
    }


    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            org.jsoup.nodes.Element element = doc.select(".cuerpo-noticia").get(0);
            news.content = element.html();

        } catch (Exception e) {
            debug("[ERROR La seleccion del articulo no se ha encontrado] tit:" + news.title);
            e.printStackTrace();
        }
        return news;
    }

} 