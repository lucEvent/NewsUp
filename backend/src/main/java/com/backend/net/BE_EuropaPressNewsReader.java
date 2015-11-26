package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

import java.io.IOException;

public class BE_EuropaPressNewsReader extends BE_NewsReader {

    public BE_EuropaPressNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Portada", "http://www.europapress.es/rss/rss.aspx"));
        SECTIONS.add(new BE_Section("Nacional", "http://www.europapress.es/rss/rss.aspx?ch=66"));
        SECTIONS.add(new BE_Section("Internacional", "http://www.europapress.es/rss/rss.aspx?ch=69"));
        SECTIONS.add(new BE_Section("Economía", "http://www.europapress.es/rss/rss.aspx?ch=136"));
        SECTIONS.add(new BE_Section("EP Social", "http://www.europapress.es/rss/rss.aspx?ch=313"));
        SECTIONS.add(new BE_Section("Deportes", "http://www.europapress.es/rss/rss.aspx?ch=67"));
        SECTIONS.add(new BE_Section("Chance", "http://www.europapress.es/rss/rss.aspx?ch=549"));
        SECTIONS.add(new BE_Section("Cultura", "http://www.europapress.es/rss/rss.aspx?ch=126"));
        SECTIONS.add(new BE_Section("Sociedad", "http://www.europapress.es/rss/rss.aspx?ch=73"));
        SECTIONS.add(new BE_Section("Motor", "http://www.europapress.es/rss/rss.aspx?ch=435"));
        SECTIONS.add(new BE_Section("Comunicados", "http://www.europapress.es/rss/rss.aspx?ch=137"));

        SECTIONS.add(new BE_Section("Autonomías", "http://www.europapress.es/rss/rss.aspx?ch=279"));
        SECTIONS.add(new BE_Section("Andalucía", "http://www.europapress.es/rss/rss.aspx?ch=279"));
        SECTIONS.add(new BE_Section("Aragón", "http://www.europapress.es/rss/rss.aspx?ch=280"));
        SECTIONS.add(new BE_Section("Asturias", "http://www.europapress.es/rss/rss.aspx?ch=294"));
        SECTIONS.add(new BE_Section("C. Valenciana", "http://www.europapress.es/rss/rss.aspx?ch=192"));
        SECTIONS.add(new BE_Section("Cantabria", "http://www.europapress.es/rss/rss.aspx?ch=281"));
        SECTIONS.add(new BE_Section("Castilla-La Mancha", "http://www.europapress.es/rss/rss.aspx?ch=282"));
        SECTIONS.add(new BE_Section("Castilla y León", "http://www.europapress.es/rss/rss.aspx?ch=283"));
        SECTIONS.add(new BE_Section("Cataluña", "http://www.europapress.es/rss/rss.aspx?ch=284 "));
        SECTIONS.add(new BE_Section("Ceuta y Melilla", "http://www.europapress.es/rss/rss.aspx?ch=310"));
        SECTIONS.add(new BE_Section("Extremadura", "http://www.europapress.es/rss/rss.aspx?ch=285"));
        SECTIONS.add(new BE_Section("Galicia", "http://www.europapress.es/rss/rss.aspx?ch=286"));
        SECTIONS.add(new BE_Section("Islas Baleares", "http://www.europapress.es/rss/rss.aspx?ch=288"));
        SECTIONS.add(new BE_Section("Islas Canarias", "http://www.europapress.es/rss/rss.aspx?ch=287"));
        SECTIONS.add(new BE_Section("La Rioja", "http://www.europapress.es/rss/rss.aspx?ch=291"));
        SECTIONS.add(new BE_Section("Madrid", "http://www.europapress.es/rss/rss.aspx?ch=289"));
        SECTIONS.add(new BE_Section("Murcia", "http://www.europapress.es/rss/rss.aspx?ch=295"));
        SECTIONS.add(new BE_Section("Navarra", "http://www.europapress.es/rss/rss.aspx?ch=293"));
        SECTIONS.add(new BE_Section("País Vasco", "http://www.europapress.es/rss/rss.aspx?ch=290"));

        SECTIONS.add(new BE_Section("Lenguas", "http://www.europapress.es/rss/rss.aspx?ch=56"));
        SECTIONS.add(new BE_Section("Euskera", "http://www.europapress.es/rss/rss.aspx?ch=58"));
        SECTIONS.add(new BE_Section("Galego", "http://www.europapress.es/rss/rss.aspx?ch=57"));
        SECTIONS.add(new BE_Section("Valencià ", "http://www.europapress.es/rss/rss.aspx?ch=60"));
        SECTIONS.add(new BE_Section("Asturianu", "http://www.europapress.es/rss/rss.aspx?ch=395"));

    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {

        org.jsoup.select.Elements img = doc.select(".schema_foto img:not([itemprop=\"articleBody\"] .schema_foto img)");
        org.jsoup.select.Elements e = doc.select("[itemprop=\"articleBody\"]");

        if (e.isEmpty()) {

            return;

        } else {

            e.select(".FechaPublicacionNoticia,.captionv2,script").remove();

        }
        news.content = img.outerHtml() + e.outerHtml();
        news.content = news.content.replace("Cargando el vídeo....", "");
    }

    protected org.jsoup.nodes.Document getDocument(String link) {
        try {
            return org.jsoup.Jsoup.connect(link).get();
        } catch (IOException e) {
        }
        return super.getDocument(link);
    }

}
