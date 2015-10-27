package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class MarcaNewsReader extends NewsReader {

    public MarcaNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Notícias principales", 0, "http://marca.feedsportal.com/rss/portada.xml"));

        SECTIONS.add(new Section("Fútbol", 0, null));
        SECTIONS.add(new Section("1ª División", 1, "http://marca.feedsportal.com/rss/futbol_1adivision.xml"));
        SECTIONS.add(new Section("2ª División", 1, "http://marca.feedsportal.com/rss/futbol_2adivision.xml"));
        SECTIONS.add(new Section("Más fútbol", 1, "http://marca.feedsportal.com/rss/futbol_mas_futbol.xml"));
        SECTIONS.add(new Section("Fútbol internacional", 1, "http://marca.feedsportal.com/rss/futbol_futbol_internacional.xml"));
        SECTIONS.add(new Section("Liga de campeones", 1, "http://marca.feedsportal.com/rss/futbol_liga_campeones.xml"));
        SECTIONS.add(new Section("Copa uefa", 1, "http://marca.feedsportal.com/rss/futbol_copa_uefa.xml"));
        SECTIONS.add(new Section("Copa del Rey", 1, "http://marca.feedsportal.com/rss/futbol_copa_rey.xml"));
        SECTIONS.add(new Section("Selección", 1, "http://marca.feedsportal.com/rss/futbol_mundial.xml"));
        SECTIONS.add(new Section("Mundial 2014", 1, "http://marca.feedsportal.com/rss/futbol_mundial.xml"));
        SECTIONS.add(new Section("Fútbol sala", 1, "http://marca.feedsportal.com/rss/futbol_futbol_sala.xml"));

        SECTIONS.add(new Section("Equipos", 0, null));
        SECTIONS.add(new Section("Athletic", 1, "http://marca.feedsportal.com/rss/futbol_equipos_athletic.xml"));
        SECTIONS.add(new Section("Atlético", 1, "http://marca.feedsportal.com/rss/futbol_equipos_atletico.xml"));
        SECTIONS.add(new Section("Barcelona", 1, "http://marca.feedsportal.com/rss/futbol_equipos_barcelona.xml"));
        SECTIONS.add(new Section("Betis", 1, "http://marca.feedsportal.com/rss/futbol_equipos_betis.xml"));
        SECTIONS.add(new Section("Celta", 1, "http://marca.feedsportal.com/rss/futbol_equipos_celta.xml"));
        SECTIONS.add(new Section("Deportivo", 1, "http://marca.feedsportal.com/rss/futbol_equipos_deportivo.xml"));
        SECTIONS.add(new Section("Espanyol", 1, "http://marca.feedsportal.com/rss/futbol_equipos_espanyol.xml"));
        SECTIONS.add(new Section("Getafe", 1, "http://marca.feedsportal.com/rss/futbol_equipos_getafe.xml"));
        SECTIONS.add(new Section("Granada", 1, "http://marca.feedsportal.com/rss/futbol_equipos_granada.xml"));
        SECTIONS.add(new Section("Levante", 1, "http://marca.feedsportal.com/rss/futbol_equipos_levante.xml"));
        SECTIONS.add(new Section("Málaga", 1, "http://marca.feedsportal.com/rss/futbol_equipos_malaga.xml"));
        SECTIONS.add(new Section("Mallorca", 1, "http://marca.feedsportal.com/rss/futbol_equipos_mallorca.xml"));
        SECTIONS.add(new Section("Osasuna", 1, "http://marca.feedsportal.com/rss/futbol_equipos_osasuna.xml"));
        SECTIONS.add(new Section("Rayo Vallecano", 1, "http://marca.feedsportal.com/rss/futbol_equipos_rayo.xml"));
        SECTIONS.add(new Section("Real Madrid", 1, "http://marca.feedsportal.com/rss/futbol_equipos_real_madrid.xml"));
        SECTIONS.add(new Section("Real Sociedad", 1, "http://marca.feedsportal.com/rss/futbol_equipos_real_sociedad.xml"));
        SECTIONS.add(new Section("Sevilla", 1, "http://marca.feedsportal.com/rss/futbol_equipos_sevilla.xml"));
        SECTIONS.add(new Section("Valencia", 1, "http://marca.feedsportal.com/rss/futbol_equipos_valencia.xml"));
        SECTIONS.add(new Section("Valladolid", 1, "http://marca.feedsportal.com/rss/futbol_equipos_valladolid.xml"));
        SECTIONS.add(new Section("Zaragoza", 1, "http://marca.feedsportal.com/rss/futbol_equipos_zaragoza.xml"));

        SECTIONS.add(new Section("Baloncesto", 0, "http://marca.feedsportal.com/rss/baloncesto.xml"));
        SECTIONS.add(new Section("ACB", 1, "http://marca.feedsportal.com/rss/baloncesto_acb.xml"));
        SECTIONS.add(new Section("NBA", 1, "http://marca.feedsportal.com/rss/baloncesto_nba.xml"));
        SECTIONS.add(new Section("Euroliga", 1, "http://marca.feedsportal.com/rss/baloncesto_euroliga.xml"));
        SECTIONS.add(new Section("Basket FEB", 1, "http://marca.feedsportal.com/rss/baloncesto_basketfeb.xml"));
        SECTIONS.add(new Section("Eurocup", 1, "http://marca.feedsportal.com/rss/baloncesto_eurocup.xml"));
        SECTIONS.add(new Section("Selección", 1, "http://marca.feedsportal.com/rss/baloncesto_seleccion.xml"));

        SECTIONS.add(new Section("Motor", 0, "http://marca.feedsportal.com/rss/motor.xml"));
        SECTIONS.add(new Section("Formula 1", 1, "http://marca.feedsportal.com/rss/motor_formula1.xml"));
        SECTIONS.add(new Section("Mundial de motos", 1, "http://marca.feedsportal.com/rss/motor_mundial_motos.xml"));
        SECTIONS.add(new Section("Rallies", 1, "http://marca.feedsportal.com/rss/motor_rallies.xml"));
        SECTIONS.add(new Section("Gp2", 1, "http://marca.feedsportal.com/rss/motor_gp2.xml"));
        SECTIONS.add(new Section("Superbikes", 1, "http://marca.feedsportal.com/rss/motor_superbikes.xml"));
        SECTIONS.add(new Section("Trial", 1, "http://marca.feedsportal.com/rss/motor_trial.xml"));
        SECTIONS.add(new Section("Más motor", 1, "http://marca.feedsportal.com/rss/motor_mas_motor.xml"));

        SECTIONS.add(new Section("Tenis", 0, "http://marca.feedsportal.com/rss/tenis.xml"));
        SECTIONS.add(new Section("Ciclismo", 0, "http://marca.feedsportal.com/rss/ciclismo.xml"));
        SECTIONS.add(new Section("Golf", 0, "http://marca.feedsportal.com/rss/golf.xml"));
        SECTIONS.add(new Section("Atletismo", 0, "http://marca.feedsportal.com/rss/atletismo.xml"));
        SECTIONS.add(new Section("Balonmano", 0, "http://marca.feedsportal.com/rss/balonmano.xml"));
        SECTIONS.add(new Section("Más deporte", 0, "http://marca.feedsportal.com/rss/mas_deportes.xml"));
        SECTIONS.add(new Section("Vela", 1, "http://marca.feedsportal.com/rss/mas_deportes_vela.xml"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        news.description = news.description.replace("�Leer", "");
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            doc.select("script").remove();

            org.jsoup.select.Elements e = doc.select("li > h4,.socialDisplay,.cuerpo_articulo > p");

            news.content = e.outerHtml();
        } catch (Exception e) {
            debug("[ERROR] link:" + news.link);
            e.printStackTrace();
        }
        return news;
    }

}
