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
        SECTIONS.add(new BE_Section("Fútbol", "http://www.lavanguardia.com/feed/deportes/futbol/index.html"));
        SECTIONS.add(new BE_Section("Baloncesto", "http://www.lavanguardia.com/feed/deportes/baloncesto/index.html"));
        SECTIONS.add(new BE_Section("Motociclismo", "http://www.lavanguardia.com/feed/deportes/motociclismo/index.html"));
        SECTIONS.add(new BE_Section("Tenis", "http://www.lavanguardia.com/feed/deportes/tenis/index.html"));
        SECTIONS.add(new BE_Section("Otros deportes", "http://www.lavanguardia.com/feed/deportes/otros/index.html"));

        SECTIONS.add(new BE_Section("Tecnología", "http://feeds.feedburner.com/lavanguardia/internet"));
        SECTIONS.add(new BE_Section("Internet", "http://www.lavanguardia.com/feed/tecnologia/internet/index.html"));

        SECTIONS.add(new BE_Section("Vida", "http://feeds.feedburner.com/lavanguardia/vida"));
        SECTIONS.add(new BE_Section("Ciencia", "http://feeds.feedburner.com/lavanguardia/ciencia"));
        SECTIONS.add(new BE_Section("Salud", "http://feeds.feedburner.com/lavanguardia/salud"));
        SECTIONS.add(new BE_Section("Medio ambiente", "http://feeds.feedburner.com/lavanguardia/medioambiente"));
        SECTIONS.add(new BE_Section("Comunicación", "http://feeds.feedburner.com/lavanguardia/comunicacion"));
        SECTIONS.add(new BE_Section("La Contra", "http://feeds.feedburner.com/lavanguardia/lacontra"));

        SECTIONS.add(new BE_Section("Cultura", "http://feeds.feedburner.com/lavanguardia/cultura"));
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
        SECTIONS.add(new BE_Section("Pekín: Isidre Ambrós", "http://feeds.feedburner.com/lavanguardia/diariodepekin"));
        SECTIONS.add(new BE_Section("Moscú: Gonzalo Aragonés", "http://feeds.feedburner.com/lavanguardia/diariodemoscu"));
        SECTIONS.add(new BE_Section("India: Jordi Joan Baños", "http://feeds.feedburner.com/lavanguardia/diariodeindia"));
        SECTIONS.add(new BE_Section("Bruselas: Beatriz Navarro", "http://feeds.feedburner.com/lavanguardia/diariodebruselas"));
        SECTIONS.add(new BE_Section("Estambul: Ricardo Ginés Echeverría", "http://feeds.feedburner.com/lavanguardia/diariodeestambul"));
        SECTIONS.add(new BE_Section("América Latina: Joaquim Ibarz", "http://feeds.feedburner.com/lavanguardia/diariodeamericalatina"));
        SECTIONS.add(new BE_Section("Buenos Aires: Robert Mur", "http://feeds.feedburner.com/lavanguardia/diariodebuenosaires"));
        SECTIONS.add(new BE_Section("París: Lluís Uría", "http://feeds.feedburner.com/lavanguardia/diariodeparis"));
        SECTIONS.add(new BE_Section("Londres: Rafael Ramos", "http://feeds.feedburner.com/lavanguardia/diariodelondres"));
        SECTIONS.add(new BE_Section("La Habana: Fernando García", "http://feeds.feedburner.com/lavanguardia/diariodelahabana"));
        SECTIONS.add(new BE_Section("Berlín: Rafael Poch", "http://feeds.feedburner.com/lavanguardia/diariodeberlin"));
        SECTIONS.add(new BE_Section("Washington: Marc Bassets", "http://feeds.feedburner.com/lavanguardia/diariodewashington"));
        SECTIONS.add(new BE_Section("Diario itinerante: Andy Robinson", "http://feeds.feedburner.com/lavanguardia/diarioitinerante"));
        SECTIONS.add(new BE_Section("Roma: Eusebio Val", "http://feeds.feedburner.com/lavanguardia/diarioderoma"));
        SECTIONS.add(new BE_Section("Jerusalén: Henrique Cymerman", "http://feeds.feedburner.com/lavanguardia/diariodejerusalen-cymerman"));

        SECTIONS.add(new BE_Section("Blogs Actualidad", null));
        SECTIONS.add(new BE_Section("No digas que se te mueren las plantas", "http://feeds.feedburner.com/lavanguardia/plantas"));
        SECTIONS.add(new BE_Section("Perspectiva europea", "http://feeds.feedburner.com/lavanguardia/perspectivaeuropea"));
        SECTIONS.add(new BE_Section("Patologías urbanas", "http://feeds.feedburner.com/lavanguardia/patologiasurbanas"));
        SECTIONS.add(new BE_Section("El Dardo", "http://feeds.feedburner.com/lavanguardia/eldardo"));
        SECTIONS.add(new BE_Section("Entre sexos", "http://feeds.feedburner.com/lavanguardia/entresexos"));
        SECTIONS.add(new BE_Section("In saecula saeculorum", "http://feeds.feedburner.com/lavanguardia/insaeculasaeculorum"));
        SECTIONS.add(new BE_Section("Por quién doblan las esquinas", "http://feeds.feedburner.com/lavanguardia/doblanesquinas"));
        SECTIONS.add(new BE_Section("La Libreta", "http://feeds.feedburner.com/lavanguardia/lalibreta"));
        SECTIONS.add(new BE_Section("El Hilo", "http://feeds.feedburner.com/lavanguardia/elhilo"));
        SECTIONS.add(new BE_Section("Metamorfosis", "http://feeds.feedburner.com/lavanguardia/metamorfosis"));
        SECTIONS.add(new BE_Section("Vía pública", "http://feeds.feedburner.com/lavanguardia/viapublica"));
        SECTIONS.add(new BE_Section("Així tot va bé", "http://feeds.feedburner.com/lavanguardia/totvabe"));
        SECTIONS.add(new BE_Section("Pasos perdidos", "http://feeds.feedburner.com/lavanguardia/pasosperdidos"));
        SECTIONS.add(new BE_Section("El otro escaño", "http://feeds.feedburner.com/lavanguardia/elotroescano"));
        SECTIONS.add(new BE_Section("Villa y Cortes", "http://feeds.feedburner.com/lavanguardia/villaycortes"));
        SECTIONS.add(new BE_Section("Valencia", "http://feeds.feedburner.com/lavanguardia/diariodevalencia"));
        SECTIONS.add(new BE_Section("Desorientado en Oriente", "http://feeds.feedburner.com/lavanguardia/desorientadoenoriente"));
        SECTIONS.add(new BE_Section("Octavillas 2.0", "http://feeds.feedburner.com/lavanguardia/octavillas"));
        SECTIONS.add(new BE_Section("El zoom", "http://feeds.feedburner.com/lavanguardia/elzoom"));
        SECTIONS.add(new BE_Section("Valor añadido", "http://feeds.feedburner.com/lavanguardia/valoranadido"));
        SECTIONS.add(new BE_Section("Guerreros del teclado", "http://feeds.feedburner.com/lavanguardia/guerrerosdelteclado"));

        SECTIONS.add(new BE_Section("Blogs Gente", null));
        SECTIONS.add(new BE_Section("Ailof", "http://feeds.feedburner.com/lavanguardia/ailof"));
        SECTIONS.add(new BE_Section("Retwitteando", "http://feeds.feedburner.com/lavanguardia/retwitteando"));
        SECTIONS.add(new BE_Section("Qué Llevas", "http://feeds.feedburner.com/lavanguardia/quellevas"));

        SECTIONS.add(new BE_Section("Blogs Tecnología", null));
        SECTIONS.add(new BE_Section("La Cafetera Rusa", "http://feeds.feedburner.com/lavanguardia/lacafeterarusa"));
        SECTIONS.add(new BE_Section("El Cuarto Bit", "http://feeds.feedburner.com/lavanguardia/elcuartobit"));
        SECTIONS.add(new BE_Section("The Fourth Bit", "http://feeds.feedburner.com/lavanguardia/thefourthbit"));
        SECTIONS.add(new BE_Section("Teclado Móvil", "http://feeds.feedburner.com/lavanguardia/tecladomovil"));

        SECTIONS.add(new BE_Section("Blogs Cultura", null));
        SECTIONS.add(new BE_Section("Punto de lectura", "http://feeds.feedburner.com/lavanguardia/puntodelectura"));
        SECTIONS.add(new BE_Section("El arquero", "http://feeds.feedburner.com/lavanguardia/elarquero"));
        SECTIONS.add(new BE_Section("Jam Session", "http://feeds.feedburner.com/lavanguardia/jamsession"));
        SECTIONS.add(new BE_Section("Ctrl+Alt+Supr", "http://feeds.feedburner.com/lavanguardia/ctrlaltsupr"));
        SECTIONS.add(new BE_Section("Sin subtítulos", "http://feeds.feedburner.com/lavanguardia/sinsubtitulos"));
        SECTIONS.add(new BE_Section("Infografía", "http://feeds.feedburner.com/lavanguardia/retwitteando"));
        SECTIONS.add(new BE_Section("Cum Laude", "http://feeds.feedburner.com/lavanguardia/cumlaude"));
        SECTIONS.add(new BE_Section("Barcelona gratis", "http://feeds.feedburner.com/lavanguardia/barcelonagratis"));

        SECTIONS.add(new BE_Section("Blogs Deportes", null));
        SECTIONS.add(new BE_Section("Planeta Mar", "http://feeds.feedburner.com/lavanguardia/planetamar"));
        SECTIONS.add(new BE_Section("Pase en profundidad", "http://feeds.feedburner.com/lavanguardia/paseenprofundidad"));
        SECTIONS.add(new BE_Section("ÑBA", "http://feeds.feedburner.com/lavanguardia/nba"));

        SECTIONS.add(new BE_Section("Blogs Ficción y Humor", null));
        SECTIONS.add(new BE_Section("El último mono", "http://feeds.feedburner.com/lavanguardia/elultimomono"));

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
        doc.select("script").remove();

        org.jsoup.select.Elements e = doc.select(".text,.video,.foto,.story-text");

        if (!e.isEmpty()) {
            e.select(".colB").remove();
            news.content = e.outerHtml();
        } else {
            e = doc.select(".entry-content");
            if (!e.isEmpty()) {
                news.content = e.html();
            }
        }
    }

}
