package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class MundoDeportivoNewsReader extends NewsReader {

    public MundoDeportivoNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Fútbol", 0, "http://www.mundodeportivo.com/feed/rss/futbol"));
        SECTIONS.add(new Section("Fichajes", 0, "http://www.mundodeportivo.com/feed/rss/futbol/fichajes"));

        SECTIONS.add(new Section("Equipos", 0, "http://www.mundodeportivo.com/feed/rss/futbol/la-liga-bbva"));
        SECTIONS.add(new Section("Almería", 1, "http://www.mundodeportivo.com/feed/rss/futbol/almeria"));
        SECTIONS.add(new Section("Athletic", 1, "http://www.mundodeportivo.com/feed/rss/futbol/athletic-bilbao"));
        SECTIONS.add(new Section("Atlético de Madrid", 1, "http://www.mundodeportivo.com/feed/rss/futbol/atletico-madrid"));
        SECTIONS.add(new Section("Celta", 1, "http://www.mundodeportivo.com/feed/rss/futbol/celta-vigo"));
        SECTIONS.add(new Section("Córdoba", 1, "http://www.mundodeportivo.com/feed/rss/futbol/cordoba"));
        SECTIONS.add(new Section("Deportivo", 1, "http://www.mundodeportivo.com/feed/rss/futbol/deportivo-coruna"));
        SECTIONS.add(new Section("Eibar", 1, "http://www.mundodeportivo.com/feed/rss/futbol/eibar"));
        SECTIONS.add(new Section("Elche", 1, "http://www.mundodeportivo.com/feed/rss/futbol/elche-cf"));
        SECTIONS.add(new Section("Espanyol", 1, "http://www.mundodeportivo.com/feed/rss/futbol/rcd-espanyol"));
        SECTIONS.add(new Section("F.C.Barcelona", 1, "http://www.mundodeportivo.com/feed/rss/futbol/fc-barcelona"));
        SECTIONS.add(new Section("Getafe", 1, "http://www.mundodeportivo.com/feed/rss/futbol/getafe"));
        SECTIONS.add(new Section("Granada", 1, "http://www.mundodeportivo.com/feed/rss/futbol/granada"));
        SECTIONS.add(new Section("Levante", 1, "http://www.mundodeportivo.com/feed/rss/futbol/levante"));
        SECTIONS.add(new Section("Málaga", 1, "http://www.mundodeportivo.com/feed/rss/futbol/malaga"));
        SECTIONS.add(new Section("Rayo Vallecano", 1, "http://www.mundodeportivo.com/feed/rss/futbol/rayo-vallecano"));
        SECTIONS.add(new Section("Real Madrid", 1, "http://www.mundodeportivo.com/feed/rss/futbol/real-madrid"));
        SECTIONS.add(new Section("Real Sociedad", 1, "http://www.mundodeportivo.com/feed/rss/futbol/real-sociedad"));
        SECTIONS.add(new Section("Sevilla", 1, "http://www.mundodeportivo.com/feed/rss/futbol/sevilla"));
        SECTIONS.add(new Section("Valencia", 1, "http://www.mundodeportivo.com/feed/rss/futbol/valencia"));
        SECTIONS.add(new Section("Villarreal", 1, "http://www.mundodeportivo.com/feed/rss/futbol/villarreal"));

        SECTIONS.add(new Section("Baloncesto", 0, "http://www.mundodeportivo.com/feed/rss/baloncesto"));
        SECTIONS.add(new Section("ACB", 1, "http://www.mundodeportivo.com/mvc/feed/rss/home"));
        SECTIONS.add(new Section("Euroliga", 1, "http://www.mundodeportivo.com/feed/rss/baloncesto/euroliga"));
        SECTIONS.add(new Section("NBA", 1, "http://www.mundodeportivo.com/feed/rss/baloncesto/nba"));

        SECTIONS.add(new Section("Motor", 0, "http://www.mundodeportivo.com/feed/rss/motor"));
        SECTIONS.add(new Section("Fórmula 1", 1, "http://www.mundodeportivo.com/feed/rss/motor/f1"));
        SECTIONS.add(new Section("Motociclismo", 1, "http://www.mundodeportivo.com/feed/rss/motor/motociclismo"));
        SECTIONS.add(new Section("Rallies", 1, "http://www.mundodeportivo.com/feed/rss/motor/rallies"));
        SECTIONS.add(new Section("Dakar", 1, "http://www.mundodeportivo.com/feed/rss/motor/rally-dakar"));
        SECTIONS.add(new Section("Más motor", 1, "http://www.mundodeportivo.com/feed/rss/motor/mas-motor"));

        SECTIONS.add(new Section("Tenis", 0, "http://www.mundodeportivo.com/feed/rss/tenis"));
        SECTIONS.add(new Section("Más deporte", 0, "http://www.mundodeportivo.com/feed/rss/mas-deporte"));

        SECTIONS.add(new Section("Opinión", 0, "http://www.mundodeportivo.com/feed/rss/opinion"));
        SECTIONS.add(new Section("¡Vaya Mundo!", 0, "http://www.mundodeportivo.com/feed/rss/vaya-mundo"));
        SECTIONS.add(new Section("Ocio", 0, "http://www.mundodeportivo.com/feed/rss/ocio"));
        SECTIONS.add(new Section("Hemeroteca", 0, "http://www.mundodeportivo.com/feed/rss/hemeroteca"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        int idash = news.description.indexOf("- ");
        if (idash != -1) news.description = news.description.substring(idash + 2);
        news.description = news.description.replace("...", "");
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            doc.select("script").remove();

            news.content = doc.select("p[itemprop=\"alternativeHeadline\"],div[itemprop=\"video\"] > iframe").outerHtml();

            org.jsoup.select.Elements imgs = doc.select(".img-responsive");
            for (org.jsoup.nodes.Element img : imgs) {
                if (img.attr("target").isEmpty()) {
                    String src = img.attr("data-src-sm");
                    if (!src.isEmpty()) news.content += "<p><img src=\"" + src + "\"></p>";
                    else news.content += img.outerHtml();
                }
            }
            org.jsoup.select.Elements e = doc.select("div[itemprop=\"articleBody\"]");
            e.select("time").remove();

            news.content += e.html();
        } catch (Exception e) {
            debug("[ERROR] link:" + news.link);
            e.printStackTrace();
        }
        return news;
    }

}
