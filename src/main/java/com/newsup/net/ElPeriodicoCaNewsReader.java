package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

public class ElPeriodicoCaNewsReader extends NewsReader {

    public ElPeriodicoCaNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Portada", 0, "http://www.elperiodico.cat/ca/rss/rss_portada.xml"));
        SECTIONS.add(new Section("Opinió", 0, "http://www.elperiodico.cat/ca/rss/opinio/rss.xml"));
        SECTIONS.add(new Section("Internacional", 0, "http://www.elperiodico.cat/ca/rss/internacional/rss.xml"));
        SECTIONS.add(new Section("Política", 0, "http://www.elperiodico.cat/ca/rss/politica/rss.xml"));
        SECTIONS.add(new Section("Societat", 0, "http://www.elperiodico.cat/ca/rss/societat/rss.xml"));
        SECTIONS.add(new Section("Economía", 0, "http://www.elperiodico.cat/ca/rss/economia/rss.xml"));
        SECTIONS.add(new Section("Tecnología", 0, "http://www.elperiodico.cat/ca/rss/tecnologia/rss.xml"));
        SECTIONS.add(new Section("Esports", 0, "http://www.elperiodico.cat/ca/rss/esports/rss.xml"));
        SECTIONS.add(new Section("Oci i cultura", 0, "http://www.elperiodico.cat/ca/rss/oci-i-cultura/rss.xml"));
        SECTIONS.add(new Section("Gent i TV", 0, "http://www.elperiodico.cat/ca/rss/gent-i-tv/rss.xml"));

        SECTIONS.add(new Section("Ciutats", 0, null));
        SECTIONS.add(new Section("Barcelona", 1, "http://www.elperiodico.cat/ca/rss/barcelona/rss.xml"));
        SECTIONS.add(new Section("L'Hospitalet", 1, "http://www.elperiodico.cat/ca/rss/hospitalet/rss.xml"));
        SECTIONS.add(new Section("Cornellà", 1, "http://www.elperiodico.cat/ca/rss/cornella/rss.xml"));
        SECTIONS.add(new Section("Sabadell", 1, "http://www.elperiodico.cat/ca/rss/sabadell/rss.xml"));
        SECTIONS.add(new Section("Terrassa", 1, "http://www.elperiodico.cat/ca/rss/terrassa/rss.xml"));
        SECTIONS.add(new Section("Badalona", 1, "http://www.elperiodico.cat/ca/rss/badalona/rss.xml"));
        SECTIONS.add(new Section("Santa Coloma", 1, "http://www.elperiodico.cat/ca/rss/santa-coloma/rss.xml"));

        SECTIONS.add(new Section("Canal Bellesa", 0, "http://www.elperiodico.cat/ca/rss/bellesa/rss.xml"));
        SECTIONS.add(new Section("Motor", 0, "http://www.elperiodico.cat/ca/rss/motor/rss.xml"));
        SECTIONS.add(new Section("Neurocàpsules", 0, "http://www.elperiodico.cat/ca/rss/neurocapsules/rss.xml"));

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
    protected Document getDocument(String rsslink) throws IOException {
        return org.jsoup.Jsoup.parse(new URL(rsslink).openStream(), "ISO-8859-1", rsslink);
    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.description = org.jsoup.Jsoup.parse(news.description).text();
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            doc.select("script").remove();
            StringBuilder sb = new StringBuilder();

            sb.append(doc.select(".subtitulo,.ep-video").outerHtml());

            org.jsoup.select.Elements e = doc.select(".slider");
            if (!e.isEmpty()) {
                sb.append(e.select("img,p").outerHtml());
            }
            sb.append(doc.select(".cuerpo-noticia").outerHtml());

            news.content = sb.toString();
        } catch (Exception e) {
            debug("[ERROR] link:" + news.link);
            e.printStackTrace();
        }
        return news;
    }

}
