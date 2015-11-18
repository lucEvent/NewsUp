package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

import java.io.IOException;
import java.net.URL;

public class BE_ElPeriodicoEsNewsReader extends BE_NewsReader {

    public BE_ElPeriodicoEsNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Portada", "http://www.elperiodico.com/es/rss/rss_portada.xml"));
        SECTIONS.add(new BE_Section("Opinión", "http://www.elperiodico.com/es/rss/opinion/rss.xml"));
        SECTIONS.add(new BE_Section("Internacional", "http://www.elperiodico.com/es/rss/internacional/rss.xml"));
        SECTIONS.add(new BE_Section("Política", "http://www.elperiodico.com/es/rss/politica/rss.xml"));
        SECTIONS.add(new BE_Section("Sociedad", "http://www.elperiodico.com/es/rss/sociedad/rss.xml"));
        SECTIONS.add(new BE_Section("Economía", "http://www.elperiodico.com/es/rss/economia/rss.xml"));
        SECTIONS.add(new BE_Section("Tecnología", "http://www.elperiodico.com/es/rss/tecnologia/rss.xml"));
        SECTIONS.add(new BE_Section("Deportes", "http://www.elperiodico.com/es/rss/deportes/rss.xml"));
        SECTIONS.add(new BE_Section("Ocio y cultura", "http://www.elperiodico.com/es/rss/ocio-y-cultura/rss.xml"));
        SECTIONS.add(new BE_Section("Gente y TV", "http://www.elperiodico.com/es/rss/gente-y-tv/rss.xml"));

        SECTIONS.add(new BE_Section("Ciudades", null));
        SECTIONS.add(new BE_Section("Barcelona", "http://www.elperiodico.com/es/rss/barcelona/rss.xml"));
        SECTIONS.add(new BE_Section("L'Hospitalet", "http://www.elperiodico.com/es/rss/hospitalet/rss.xml"));
        SECTIONS.add(new BE_Section("Cornellà", "http://www.elperiodico.com/es/rss/cornella/rss.xml"));
        SECTIONS.add(new BE_Section("Sabadell", "http://www.elperiodico.com/es/rss/sabadell/rss.xml"));
        SECTIONS.add(new BE_Section("Terrassa", "http://www.elperiodico.com/es/rss/terrassa/rss.xml"));
        SECTIONS.add(new BE_Section("Badalona", "http://www.elperiodico.com/es/rss/badalona/rss.xml"));
        SECTIONS.add(new BE_Section("Santa Coloma", "http://www.elperiodico.com/es/rss/santa-coloma/rss.xml"));

        SECTIONS.add(new BE_Section("Canal Belleza", "http://www.elperiodico.com/es/rss/belleza/rss.xml"));
        SECTIONS.add(new BE_Section("Motor", "http://www.elperiodico.com/es/rss/motor/rss.xml"));

        SECTIONS.add(new BE_Section("Blogs", null));
        SECTIONS.add(new BE_Section("Los restaurantes de Pau Arenós", "http://rdp.elperiodico.com/feed/?_ga=1.183745446.1073564764.1445817152"));
        SECTIONS.add(new BE_Section("Your disco needs you", "http://blogs.timeout.cat/yourdisconeedsyou/feed/"));
        SECTIONS.add(new BE_Section("I can hear music", "http://blogs.timeout.cat/icanhearmusic/feed/"));
        SECTIONS.add(new BE_Section("Brutus", "http://blogs.timeout.cat/brutus/feed/"));
        SECTIONS.add(new BE_Section("Olor de tinta", "http://blogs.timeout.cat/olor-de-tinta/feed/"));
        SECTIONS.add(new BE_Section("L'Escuradents", "http://blogs.timeout.cat/escuradents/feed/"));
        SECTIONS.add(new BE_Section("Literatura barata", "http://blogs.timeout.cat/literatura-barata/feed/"));
        SECTIONS.add(new BE_Section("Addictes al 8è art", "http://blogs.timeout.cat/addictes-al-8e-art/feed/"));
        SECTIONS.add(new BE_Section("Cuaderno de viaje", "http://blogs.elperiodico.com/viajes/feed/?_ga=1.183745446.1073564764.1445817152"));
        SECTIONS.add(new BE_Section("El Tourmalet", "http://tourmalet.elperiodico.com/feed/?_ga=1.183745446.1073564764.1445817152"));
        SECTIONS.add(new BE_Section("Rumbo a la Casa Blanca #USA2016", "http://blogs.elperiodico.com/rumbo-casa-blanca/feed/?_ga=1.183745446.1073564764.1445817152"));
        SECTIONS.add(new BE_Section("Bloglobal", "http://blogs.elperiodico.com/bloglobal/feed/?_ga=1.183745446.1073564764.1445817152"));
        SECTIONS.add(new BE_Section("Destinos ", "http://www.visitdestinos.com/feed/"));
        SECTIONS.add(new BE_Section("+ Digital", "http://blogs.elperiodico.com/masdigital/feed?_ga=1.183745446.1073564764.1445817152"));

    }

    @Override
    protected org.jsoup.nodes.Document getDocument(String rsslink) {
        if (rsslink == SECTIONS.get(0).link) {
            try {
                return org.jsoup.Jsoup.parse(new URL(rsslink).openStream(), "ISO-8859-1", rsslink);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.getDocument(rsslink);
    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        if (!content.isEmpty()) {
            news.content = content;
        }
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        doc.select("script").remove();

        org.jsoup.select.Elements intro = doc.select(".ep-video,.unit > .ep-img,.unit > .ep-galeria");
        intro.select(".carousel").remove();
        intro = intro.select("img");

        org.jsoup.select.Elements content = doc.select(".cuerpo-noticia,.cuerpo-opinion");

        content.select(".fecha,.carousel,.thumb-pie,.cred").remove();
        content = content.select("p,a > img,h2,h3,h4,h5,h6");

        news.content = intro.outerHtml() + content.outerHtml();

        if (news.content.length() < 80) {
            news.content = null;
        }
    }

}
