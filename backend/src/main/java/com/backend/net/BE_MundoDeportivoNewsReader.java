package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_MundoDeportivoNewsReader extends BE_NewsReader {

    public BE_MundoDeportivoNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Fútbol", "http://www.mundodeportivo.com/feed/rss/futbol"));
        SECTIONS.add(new BE_Section("Fichajes", "http://www.mundodeportivo.com/feed/rss/futbol/fichajes"));

        SECTIONS.add(new BE_Section("Equipos", "http://www.mundodeportivo.com/feed/rss/futbol/la-liga-bbva"));
        SECTIONS.add(new BE_Section("Almería", "http://www.mundodeportivo.com/feed/rss/futbol/almeria"));
        SECTIONS.add(new BE_Section("Athletic", "http://www.mundodeportivo.com/feed/rss/futbol/athletic-bilbao"));
        SECTIONS.add(new BE_Section("Atlético de Madrid", "http://www.mundodeportivo.com/feed/rss/futbol/atletico-madrid"));
        SECTIONS.add(new BE_Section("Celta", "http://www.mundodeportivo.com/feed/rss/futbol/celta-vigo"));
        SECTIONS.add(new BE_Section("Córdoba", "http://www.mundodeportivo.com/feed/rss/futbol/cordoba"));
        SECTIONS.add(new BE_Section("Deportivo", "http://www.mundodeportivo.com/feed/rss/futbol/deportivo-coruna"));
        SECTIONS.add(new BE_Section("Eibar", "http://www.mundodeportivo.com/feed/rss/futbol/eibar"));
        SECTIONS.add(new BE_Section("Elche", "http://www.mundodeportivo.com/feed/rss/futbol/elche-cf"));
        SECTIONS.add(new BE_Section("Espanyol", "http://www.mundodeportivo.com/feed/rss/futbol/rcd-espanyol"));
        SECTIONS.add(new BE_Section("F.C.Barcelona", "http://www.mundodeportivo.com/feed/rss/futbol/fc-barcelona"));
        SECTIONS.add(new BE_Section("Getafe", "http://www.mundodeportivo.com/feed/rss/futbol/getafe"));
        SECTIONS.add(new BE_Section("Granada", "http://www.mundodeportivo.com/feed/rss/futbol/granada"));
        SECTIONS.add(new BE_Section("Levante", "http://www.mundodeportivo.com/feed/rss/futbol/levante"));
        SECTIONS.add(new BE_Section("Málaga", "http://www.mundodeportivo.com/feed/rss/futbol/malaga"));
        SECTIONS.add(new BE_Section("Rayo Vallecano", "http://www.mundodeportivo.com/feed/rss/futbol/rayo-vallecano"));
        SECTIONS.add(new BE_Section("Real Madrid", "http://www.mundodeportivo.com/feed/rss/futbol/real-madrid"));
        SECTIONS.add(new BE_Section("Real Sociedad", "http://www.mundodeportivo.com/feed/rss/futbol/real-sociedad"));
        SECTIONS.add(new BE_Section("Sevilla", "http://www.mundodeportivo.com/feed/rss/futbol/sevilla"));
        SECTIONS.add(new BE_Section("Valencia", "http://www.mundodeportivo.com/feed/rss/futbol/valencia"));
        SECTIONS.add(new BE_Section("Villarreal", "http://www.mundodeportivo.com/feed/rss/futbol/villarreal"));

        SECTIONS.add(new BE_Section("Baloncesto", "http://www.mundodeportivo.com/feed/rss/baloncesto"));
        SECTIONS.add(new BE_Section("ACB", "http://www.mundodeportivo.com/mvc/feed/rss/home"));
        SECTIONS.add(new BE_Section("Euroliga", "http://www.mundodeportivo.com/feed/rss/baloncesto/euroliga"));
        SECTIONS.add(new BE_Section("NBA", "http://www.mundodeportivo.com/feed/rss/baloncesto/nba"));

        SECTIONS.add(new BE_Section("Motor", "http://www.mundodeportivo.com/feed/rss/motor"));
        SECTIONS.add(new BE_Section("Fórmula 1", "http://www.mundodeportivo.com/feed/rss/motor/f1"));
        SECTIONS.add(new BE_Section("Motociclismo", "http://www.mundodeportivo.com/feed/rss/motor/motociclismo"));
        SECTIONS.add(new BE_Section("Rallies", "http://www.mundodeportivo.com/feed/rss/motor/rallies"));
        SECTIONS.add(new BE_Section("Dakar", "http://www.mundodeportivo.com/feed/rss/motor/rally-dakar"));
        SECTIONS.add(new BE_Section("Más motor", "http://www.mundodeportivo.com/feed/rss/motor/mas-motor"));

        SECTIONS.add(new BE_Section("Tenis", "http://www.mundodeportivo.com/feed/rss/tenis"));
        SECTIONS.add(new BE_Section("Más deporte", "http://www.mundodeportivo.com/feed/rss/mas-deporte"));

        SECTIONS.add(new BE_Section("Opinión", "http://www.mundodeportivo.com/feed/rss/opinion"));
        SECTIONS.add(new BE_Section("¡Vaya Mundo!", "http://www.mundodeportivo.com/feed/rss/vaya-mundo"));
        SECTIONS.add(new BE_Section("Ocio", "http://www.mundodeportivo.com/feed/rss/ocio"));
        SECTIONS.add(new BE_Section("Hemeroteca", "http://www.mundodeportivo.com/feed/rss/hemeroteca"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        int idash = news.description.indexOf("- ");
        if (idash != -1) news.description = news.description.substring(idash + 2);
        news.description = news.description.replace("...", "");
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        String intro = doc.select("[itemprop=\"alternativeHeadline\"]").outerHtml();
        org.jsoup.select.Elements imgs = doc.select("[itemprop=\"image\"] img,.gallery-leaf-figure img");

        StringBuilder img = new StringBuilder();
        if (!imgs.isEmpty()) {
            for (org.jsoup.nodes.Element i : imgs) {
                String attr = i.attr("data-src-md");
                if (attr.isEmpty())
                    attr = i.attr("src");

                img.append("<img src=\"" + attr + "\">");
            }
        }
        org.jsoup.select.Elements metas = doc.select("[itemprop=\"video\"] [itemprop=\"image\"]");
        if (!metas.isEmpty()) {
            for (org.jsoup.nodes.Element i : metas) {
                img.append("<img src=\"" + i.attr("content") + "\">");
            }
        }

        org.jsoup.select.Elements content = doc.select("[itemprop=\"articleBody\"]");
        content.select(".datetime-story-leaf,.gallery-story-leaf-figcaption").remove();

        news.content = intro + img.toString() + content.html();
    }

}
