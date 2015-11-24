package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_LaVanguardiaNewsReader extends BE_NewsReader {

    public BE_LaVanguardiaNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Última hora", "http://feeds.feedburner.com/lavanguardia/alminuto"));
        SECTIONS.add(new BE_Section("Top Noticias", "http://feeds.feedburner.com/lavanguardia/home"));

        SECTIONS.add(new BE_Section("Internacional", "http://feeds.feedburner.com/lavanguardia/internacional"));
        SECTIONS.add(new BE_Section("Política", "http://feeds.feedburner.com/lavanguardia/politica"));
        SECTIONS.add(new BE_Section("Economía", "http://feeds.feedburner.com/lavanguardia/economia"));
        SECTIONS.add(new BE_Section("Sucesos", "http://feeds.feedburner.com/lavanguardia/sucesos"));
        SECTIONS.add(new BE_Section("Deportes", "http://feeds.feedburner.com/lavanguardia/deportes"));
        SECTIONS.add(new BE_Section("Tecnología", "http://feeds.feedburner.com/lavanguardia/internet"));

        SECTIONS.add(new BE_Section("Vida", "http://feeds.feedburner.com/lavanguardia/vida"));
        SECTIONS.add(new BE_Section("Ciencia", "http://feeds.feedburner.com/lavanguardia/ciencia"));
        SECTIONS.add(new BE_Section("Comunicación", "http://feeds.feedburner.com/lavanguardia/comunicacion"));
        SECTIONS.add(new BE_Section("La Contra", "http://feeds.feedburner.com/lavanguardia/lacontra"));

        SECTIONS.add(new BE_Section("Cultura", null));
        SECTIONS.add(new BE_Section("Música", "http://feeds.feedburner.com/lavanguardia/musica"));
        SECTIONS.add(new BE_Section("Cine", "http://feeds.feedburner.com/lavanguardia/cine"));
        SECTIONS.add(new BE_Section("Libros", "http://feeds.feedburner.com/lavanguardia/libros"));
        SECTIONS.add(new BE_Section("Escenarios", "http://feeds.feedburner.com/lavanguardia/escenarios"));

        SECTIONS.add(new BE_Section("Gente", "http://feeds.feedburner.com/lavanguardia/gente"));

        SECTIONS.add(new BE_Section("Ocio", "http://feeds.feedburner.com/lavanguardia/ocio"));
        SECTIONS.add(new BE_Section("Televisión", "http://feeds.feedburner.com/lavanguardia/television"));
        SECTIONS.add(new BE_Section("Viajes", "http://feeds.feedburner.com/lavanguardia/viajes"));
        SECTIONS.add(new BE_Section("Motor", "http://feeds.feedburner.com/lavanguardia/motor"));

        SECTIONS.add(new BE_Section("Participación", "http://feeds.feedburner.com/lavanguardia/participacion"));

        SECTIONS.add(new BE_Section("Corresponsales", null));
        SECTIONS.add(new BE_Section("Beirut: Tomás Alcoverro", "http://feeds.feedburner.com/lavanguardia/diariodebeirut"));
        SECTIONS.add(new BE_Section("India: Jordi Joan Baños", "http://feeds.feedburner.com/lavanguardia/diariodeindia"));
        SECTIONS.add(new BE_Section("Diario itinerante: Andy Robinson", "http://feeds.feedburner.com/lavanguardia/diarioitinerante"));
        SECTIONS.add(new BE_Section("Roma: Eusebio Val", "http://feeds.feedburner.com/lavanguardia/diarioderoma"));

        SECTIONS.add(new BE_Section("Blogs Actualidad", null));
        SECTIONS.add(new BE_Section("No digas que se te mueren las plantas", "http://feeds.feedburner.com/lavanguardia/plantas"));
        SECTIONS.add(new BE_Section("El Dardo", "http://feeds.feedburner.com/lavanguardia/eldardo"));
        SECTIONS.add(new BE_Section("Metamorfosis", "http://feeds.feedburner.com/lavanguardia/metamorfosis"));
        SECTIONS.add(new BE_Section("Pasos perdidos", "http://feeds.feedburner.com/lavanguardia/pasosperdidos"));
        SECTIONS.add(new BE_Section("El otro escaño", "http://feeds.feedburner.com/lavanguardia/elotroescano"));
        SECTIONS.add(new BE_Section("Valencia", "http://feeds.feedburner.com/lavanguardia/diariodevalencia"));
        SECTIONS.add(new BE_Section("Desorientado en Oriente", "http://feeds.feedburner.com/lavanguardia/desorientadoenoriente"));
        SECTIONS.add(new BE_Section("Valor añadido", "http://feeds.feedburner.com/lavanguardia/valoranadido"));

        SECTIONS.add(new BE_Section("Blogs Gente", null));
        SECTIONS.add(new BE_Section("Ailof", "http://feeds.feedburner.com/lavanguardia/ailof"));
        SECTIONS.add(new BE_Section("Qué Llevas", "http://feeds.feedburner.com/lavanguardia/quellevas"));

        SECTIONS.add(new BE_Section("Blogs Tecnología", null));
        SECTIONS.add(new BE_Section("The Fourth Bit", "http://feeds.feedburner.com/lavanguardia/thefourthbit"));
        SECTIONS.add(new BE_Section("Teclado Móvil", "http://feeds.feedburner.com/lavanguardia/tecladomovil"));

        SECTIONS.add(new BE_Section("Blogs Cultura", null));
        SECTIONS.add(new BE_Section("El arquero", "http://feeds.feedburner.com/lavanguardia/elarquero"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        news.description = org.jsoup.Jsoup.parseBodyFragment(news.description).text();
        int idash = news.description.indexOf("- ");
        if (idash != -1) news.description = news.description.substring(idash + 2);
        return news;
    }

    @Override
    protected void readNewsContent(org.jsoup.nodes.Document doc, BE_News news) {
        org.jsoup.select.Elements e = doc.select("[itemprop=\"articleBody\"]");

        for (org.jsoup.nodes.Element style : e.select("[style]")) {
            style.removeAttr("style");
        }

        if (!e.isEmpty()) {
            news.content = e.html();
        }
    }

}
