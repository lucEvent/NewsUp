package com.newsup.net;

import android.content.Context;
import android.os.Handler;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class AsNewsReader extends NewsReader {

    public AsNewsReader(Handler handler, Context context) {
        super(handler, context);

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("TITULARES", 0, "http://as.com/rss/diarioas/portada.xml"));
        SECTIONS.add(new Section("ULTIMAS NOTICIAS", 0, "http://as.com/rss/tags/ultimas_noticias.xml"));

        SECTIONS.add(new Section("FÚTBOL", 0, "http://futbol.as.com/rss/futbol/portada.xml"));
        SECTIONS.add(new Section("LIGA BBVA", 1, "http://futbol.as.com/rss/futbol/primera.xml"));
        SECTIONS.add(new Section("LIGA ADELANTE", 1, "http://futbol.as.com/rss/futbol/segunda.xml"));
        SECTIONS.add(new Section("COPA DEL REY", 1, "http://futbol.as.com/rss/futbol/copa_del_rey.xml"));
        SECTIONS.add(new Section("CHAMPIONS LEAGUE", 1, "http://futbol.as.com/rss/futbol/champions.xml"));
        SECTIONS.add(new Section("EUROPA LEAGUE", 1, "http://futbol.as.com/rss/futbol/uefa.xml"));
        SECTIONS.add(new Section("SEGUNDA B", 1, "http://masdeporte.as.com/tag/rss/segunda_division_b/a"));
        SECTIONS.add(new Section("MUNDIAL", 1, "http://futbol.as.com/rss/futbol/mundial.xml"));
        SECTIONS.add(new Section("INTERNACIONAL", 1, "http://futbol.as.com/rss/futbol/internacional.xml"));

        SECTIONS.add(new Section("MOTOR", 0, "http://motor.as.com/rss/motor/portada.xml"));
        SECTIONS.add(new Section("FORMULA 1", 1, "http://motor.as.com/rss/motor/formula_1.xml"));
        SECTIONS.add(new Section("MOTOCICLISMO", 1, "http://motor.as.com/rss/motor/motociclismo.xml"));
        SECTIONS.add(new Section("MÁS MOTOR", 1, "http://motor.as.com/rss/motor/mas_motor.xml"));

        SECTIONS.add(new Section("BALONCESTO", 0, "http://motor.as.com/rss/baloncesto/portada.xml"));
        SECTIONS.add(new Section("LIGA ENDESA", 1, "http://baloncesto.as.com/rss/baloncesto/acb.xml"));
        SECTIONS.add(new Section("NBA", 1, "http://baloncesto.as.com/rss/baloncesto/nba.xml"));
        SECTIONS.add(new Section("EUROLIGA", 1, "http://as.com/rss/baloncesto/euroliga.xml"));
        SECTIONS.add(new Section("COPA DEL REY", 1, "http://as.com/rss/baloncesto/copa_del_rey.xml"));
        SECTIONS.add(new Section("EUROCUP", 1, "http://as.com/rss/baloncesto/eurocup.xml"));
        SECTIONS.add(new Section("MÁS BALONCESTO", 1, "http://as.com/rss/baloncesto/mas_baloncesto.xml"));

        SECTIONS.add(new Section("TENIS", 0, "http://tenis.as.com/rss/tenis/portada.xml"));
        SECTIONS.add(new Section("CICLISMO", 0, "http://as.com/rss/ciclismo/portada.xml"));
        SECTIONS.add(new Section("ATLETISMO", 0, "http://as.com/rss/masdeporte/atletismo.xml"));
        SECTIONS.add(new Section("BALONMANO", 0, "http://as.com/rss/masdeporte/balonmano.xml"));
        SECTIONS.add(new Section("GOLF", 0, "http://as.com/rss/masdeporte/golf.xml"));
        SECTIONS.add(new Section("JUEGOS OLIMPICOS", 0, "http://as.com/rss/masdeporte/juegosolimpicos.xml"));
        SECTIONS.add(new Section("POLIDEPORTIVO", 0, "http://as.com/rss/masdeporte/polideportivo.xml"));
        SECTIONS.add(new Section("MÁS DEPORTES", 0, "http://as.com/rss/masdeporte/portada.xml"));

        SECTIONS.add(new Section("OPINION", 0, "http://as.com/rss/opinion/portada.xml"));

        SECTIONS.add(new Section("EQUIPOS", 0, null));
        SECTIONS.add(new Section("ALMERÍA", 1, "http://masdeporte.as.com/tag/rss/ud_almeria/a"));
        SECTIONS.add(new Section("ATLÉTICO DE MADRID", 1, "http://masdeporte.as.com/tag/rss/atletico_madrid/a"));
        SECTIONS.add(new Section("ATHLETIC DE BILBAO", 1, "http://masdeporte.as.com/tag/rss/athletic/a"));
        SECTIONS.add(new Section("BARCELONA", 1, "http://masdeporte.as.com/tag/rss/fc_barcelona/a"));
        SECTIONS.add(new Section("BETIS", 1, "http://masdeporte.as.com/tag/rss/real_betis/a"));
        SECTIONS.add(new Section("CELTA", 1, "http://masdeporte.as.com/tag/rss/real_club_celta_de_vigo/a"));
        SECTIONS.add(new Section("ELCHE", 1, "http://masdeporte.as.com/tag/rss/elche_cf/a"));
        SECTIONS.add(new Section("ESPANYOL", 1, "http://masdeporte.as.com/tag/rss/rcd_espanyol/a"));
        SECTIONS.add(new Section("GETAFE", 1, "http://masdeporte.as.com/tag/rss/getafe_cf/a"));
        SECTIONS.add(new Section("GRANADA", 1, "http://masdeporte.as.com/tag/rss/granada_cf/a"));
        SECTIONS.add(new Section("LEVANTE", 1, "http://masdeporte.as.com/tag/rss/levante_ud/a"));
        SECTIONS.add(new Section("MÁLAGA", 1, "http://masdeporte.as.com/tag/rss/malaga_cf/a"));
        SECTIONS.add(new Section("OSASUNA", 1, "http://masdeporte.as.com/tag/rss/osasuna/a"));
        SECTIONS.add(new Section("RAYO VALLECANO", 1, "http://masdeporte.as.com/tag/rss/rayo_vallecano/a"));
        SECTIONS.add(new Section("REAL MADRID", 1, "http://masdeporte.as.com/tag/rss/real_madrid/a"));
        SECTIONS.add(new Section("REAL SOCIEDAD", 1, "http://masdeporte.as.com/tag/rss/real_sociedad/a"));
        SECTIONS.add(new Section("SEVILLA", 1, "http://masdeporte.as.com/tag/rss/sevilla_futbol_club/a"));
        SECTIONS.add(new Section("VALENCIA", 1, "http://masdeporte.as.com/tag/rss/valencia_cf/a"));
        SECTIONS.add(new Section("VALLADOLID", 1, "http://masdeporte.as.com/tag/rss/real_valladolid/a"));
        SECTIONS.add(new Section("VILLARREAL", 1, "http://masdeporte.as.com/tag/rss/villarreal_cf/a"));

        SECTIONS.add(new Section("AUTORES", 0, null));
        SECTIONS.add(new Section("ALFREDO RELAÑO", 1, "http://masdeporte.as.com/autor/rss/alfredo_relano/a"));
        SECTIONS.add(new Section("JUAN MORA", 1, "http://masdeporte.as.com/autor/rss/juan_mora/a"));
        SECTIONS.add(new Section("ALEJANDRO ELORTEGUI", 1, "http://masdeporte.as.com/autor/rss/alejandro_elortegui/a"));
        SECTIONS.add(new Section("JUANMA TRUEBA", 1, "http://masdeporte.as.com/autor/rss/juanma_trueba/a"));
        SECTIONS.add(new Section("TOMÁS RONCERO", 1, "http://masdeporte.as.com/autor/rss/tomas_roncero/a"));
        SECTIONS.add(new Section("MANOLETE", 1, "http://masdeporte.as.com/autor/rss/manolete/a"));
        SECTIONS.add(new Section("JAVIER MATALLANAS", 1, "http://masdeporte.as.com/autor/rss/javier_g._matallanas/a"));

    }

    @Override
    public News readNewsContent(News news) {
        org.jsoup.nodes.Document doc = getNewsPage(news);
        if (doc == null) return news;

        try {
            org.jsoup.nodes.Element element = doc.getElementsByAttributeValue("itemprop", "articleBody").get(0);
            news.content = element.html();

        } catch (Exception e) {
            debug("[ERROR La seleccion del articulo no se ha encontrado] " + news.link);
            e.printStackTrace();
        }
        return news;
    }

    protected void debug(String text) {
        android.util.Log.d("##ASNewsReader##", text);
    }

}
