package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

import java.io.IOException;
import java.net.URL;

public class ElPeriodicoEsNewsReader extends NewsReader {

    public ElPeriodicoEsNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Portada", 0, "http://www.elperiodico.com/es/rss/rss_portada.xml"));
        SECTIONS.add(new Section("Opinión", 0, "http://www.elperiodico.com/es/rss/opinion/rss.xml"));
        SECTIONS.add(new Section("Internacional", 0, "http://www.elperiodico.com/es/rss/internacional/rss.xml"));
        SECTIONS.add(new Section("Política", 0, "http://www.elperiodico.com/es/rss/politica/rss.xml"));
        SECTIONS.add(new Section("Sociedad", 0, "http://www.elperiodico.com/es/rss/sociedad/rss.xml"));
        SECTIONS.add(new Section("Economía", 0, "http://www.elperiodico.com/es/rss/economia/rss.xml"));
        SECTIONS.add(new Section("Tecnología", 0, "http://www.elperiodico.com/es/rss/tecnologia/rss.xml"));
        SECTIONS.add(new Section("Deportes", 0, "http://www.elperiodico.com/es/rss/deportes/rss.xml"));
        SECTIONS.add(new Section("Ocio y cultura", 0, "http://www.elperiodico.com/es/rss/ocio-y-cultura/rss.xml"));
        SECTIONS.add(new Section("Gente y TV", 0, "http://www.elperiodico.com/es/rss/gente-y-tv/rss.xml"));

        SECTIONS.add(new Section("Ciudades", 0, null));
        SECTIONS.add(new Section("Barcelona", 1, "http://www.elperiodico.com/es/rss/barcelona/rss.xml"));
        SECTIONS.add(new Section("L'Hospitalet", 1, "http://www.elperiodico.com/es/rss/hospitalet/rss.xml"));
        SECTIONS.add(new Section("Cornellà", 1, "http://www.elperiodico.com/es/rss/cornella/rss.xml"));
        SECTIONS.add(new Section("Sabadell", 1, "http://www.elperiodico.com/es/rss/sabadell/rss.xml"));
        SECTIONS.add(new Section("Terrassa", 1, "http://www.elperiodico.com/es/rss/terrassa/rss.xml"));
        SECTIONS.add(new Section("Badalona", 1, "http://www.elperiodico.com/es/rss/badalona/rss.xml"));
        SECTIONS.add(new Section("Santa Coloma", 1, "http://www.elperiodico.com/es/rss/santa-coloma/rss.xml"));

        SECTIONS.add(new Section("Canal Belleza", 0, "http://www.elperiodico.com/es/rss/belleza/rss.xml"));
        SECTIONS.add(new Section("Motor", 0, "http://www.elperiodico.com/es/rss/motor/rss.xml"));

        SECTIONS.add(new Section("Blogs", 0, null));
        SECTIONS.add(new Section("Los restaurantes de Pau Arenós", 1, "http://rdp.elperiodico.com/feed/?_ga=1.183745446.1073564764.1445817152"));
        SECTIONS.add(new Section("Your disco needs you", 1, "http://blogs.timeout.cat/yourdisconeedsyou/feed/"));
        SECTIONS.add(new Section("I can hear music", 1, "http://blogs.timeout.cat/icanhearmusic/feed/"));
        SECTIONS.add(new Section("Brutus", 1, "http://blogs.timeout.cat/brutus/feed/"));
        SECTIONS.add(new Section("Olor de tinta", 1, "http://blogs.timeout.cat/olor-de-tinta/feed/"));
        SECTIONS.add(new Section("L'Escuradents", 1, "http://blogs.timeout.cat/escuradents/feed/"));
        SECTIONS.add(new Section("Literatura barata", 1, "http://blogs.timeout.cat/literatura-barata/feed/"));
        SECTIONS.add(new Section("Addictes al 8è art", 1, "http://blogs.timeout.cat/addictes-al-8e-art/feed/"));
        SECTIONS.add(new Section("Cuaderno de viaje", 1, "http://blogs.elperiodico.com/viajes/feed/?_ga=1.183745446.1073564764.1445817152"));
        SECTIONS.add(new Section("El Tourmalet", 1, "http://tourmalet.elperiodico.com/feed/?_ga=1.183745446.1073564764.1445817152"));
        SECTIONS.add(new Section("Rumbo a la Casa Blanca #USA2016", 1, "http://blogs.elperiodico.com/rumbo-casa-blanca/feed/?_ga=1.183745446.1073564764.1445817152"));
        SECTIONS.add(new Section("Bloglobal", 1, "http://blogs.elperiodico.com/bloglobal/feed/?_ga=1.183745446.1073564764.1445817152"));
        SECTIONS.add(new Section("Destinos ", 1, "http://www.visitdestinos.com/feed/"));
        SECTIONS.add(new Section("+ Digital", 1, "http://blogs.elperiodico.com/masdigital/feed?_ga=1.183745446.1073564764.1445817152"));

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
    protected News applySpecialCase(News news, String content) {
        if (!content.isEmpty()) {
            news.content = content;
        }
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, News news) {
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
