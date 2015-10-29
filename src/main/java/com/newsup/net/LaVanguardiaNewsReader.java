package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class LaVanguardiaNewsReader extends NewsReader {

    public LaVanguardiaNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Última hora", 0, "http://feeds.feedburner.com/lavanguardia/alminuto"));
        SECTIONS.add(new Section("Top Noticias", 0, "http://feeds.feedburner.com/lavanguardia/home"));

        SECTIONS.add(new Section("Internacional", 0, "http://feeds.feedburner.com/lavanguardia/internacional"));
        SECTIONS.add(new Section("Política", 0, "http://feeds.feedburner.com/lavanguardia/politica"));
        SECTIONS.add(new Section("Economía", 0, "http://feeds.feedburner.com/lavanguardia/economia"));
        SECTIONS.add(new Section("Sucesos", 0, "http://feeds.feedburner.com/lavanguardia/sucesos"));

        SECTIONS.add(new Section("Deportes", 0, "http://feeds.feedburner.com/lavanguardia/deportes"));
        SECTIONS.add(new Section("Fútbol", 1, "http://www.lavanguardia.com/feed/deportes/futbol/index.html"));
        SECTIONS.add(new Section("Baloncesto", 1, "http://www.lavanguardia.com/feed/deportes/baloncesto/index.html"));
        SECTIONS.add(new Section("Motociclismo", 1, "http://www.lavanguardia.com/feed/deportes/motociclismo/index.html"));
        SECTIONS.add(new Section("Tenis", 1, "http://www.lavanguardia.com/feed/deportes/tenis/index.html"));
        SECTIONS.add(new Section("Otros deportes", 1, "http://www.lavanguardia.com/feed/deportes/otros/index.html"));

        SECTIONS.add(new Section("Tecnología", 0, "http://feeds.feedburner.com/lavanguardia/internet"));
        SECTIONS.add(new Section("Internet", 1, "http://www.lavanguardia.com/feed/tecnologia/internet/index.html"));

        SECTIONS.add(new Section("Vida", 0, "http://feeds.feedburner.com/lavanguardia/vida"));
        SECTIONS.add(new Section("Ciencia", 1, "http://feeds.feedburner.com/lavanguardia/ciencia"));
        SECTIONS.add(new Section("Salud", 1, "http://feeds.feedburner.com/lavanguardia/salud"));
        SECTIONS.add(new Section("Medio ambiente", 1, "http://feeds.feedburner.com/lavanguardia/medioambiente"));
        SECTIONS.add(new Section("Comunicación", 1, "http://feeds.feedburner.com/lavanguardia/comunicacion"));
        SECTIONS.add(new Section("La Contra", 1, "http://feeds.feedburner.com/lavanguardia/lacontra"));

        SECTIONS.add(new Section("Cultura", 0, "http://feeds.feedburner.com/lavanguardia/cultura"));
        SECTIONS.add(new Section("Música", 1, "http://feeds.feedburner.com/lavanguardia/musica"));
        SECTIONS.add(new Section("Cine", 1, "http://feeds.feedburner.com/lavanguardia/cine"));
        SECTIONS.add(new Section("Libros", 1, "http://feeds.feedburner.com/lavanguardia/libros"));
        SECTIONS.add(new Section("Escenarios", 1, "http://feeds.feedburner.com/lavanguardia/escenarios"));

        SECTIONS.add(new Section("Gente", 0, "http://feeds.feedburner.com/lavanguardia/gente"));

        SECTIONS.add(new Section("Ocio", 0, "http://feeds.feedburner.com/lavanguardia/ocio"));
        SECTIONS.add(new Section("Televisión", 1, "http://feeds.feedburner.com/lavanguardia/television"));
        SECTIONS.add(new Section("Viajes", 1, "http://feeds.feedburner.com/lavanguardia/viajes"));
        SECTIONS.add(new Section("Motor", 1, "http://feeds.feedburner.com/lavanguardia/motor"));

        SECTIONS.add(new Section("Participación", 0, "http://feeds.feedburner.com/lavanguardia/participacion"));

        SECTIONS.add(new Section("Corresponsales", 0, null));
        SECTIONS.add(new Section("Beirut: Tomás Alcoverro", 1, "http://feeds.feedburner.com/lavanguardia/diariodebeirut"));
        SECTIONS.add(new Section("Pekín: Isidre Ambrós", 1, "http://feeds.feedburner.com/lavanguardia/diariodepekin"));
        SECTIONS.add(new Section("Moscú: Gonzalo Aragonés", 1, "http://feeds.feedburner.com/lavanguardia/diariodemoscu"));
        SECTIONS.add(new Section("India: Jordi Joan Baños", 1, "http://feeds.feedburner.com/lavanguardia/diariodeindia"));
        SECTIONS.add(new Section("Bruselas: Beatriz Navarro", 1, "http://feeds.feedburner.com/lavanguardia/diariodebruselas"));
        SECTIONS.add(new Section("Estambul: Ricardo Ginés Echeverría", 1, "http://feeds.feedburner.com/lavanguardia/diariodeestambul"));
        SECTIONS.add(new Section("América Latina: Joaquim Ibarz", 1, "http://feeds.feedburner.com/lavanguardia/diariodeamericalatina"));
        SECTIONS.add(new Section("Buenos Aires: Robert Mur", 1, "http://feeds.feedburner.com/lavanguardia/diariodebuenosaires"));
        SECTIONS.add(new Section("París: Lluís Uría", 1, "http://feeds.feedburner.com/lavanguardia/diariodeparis"));
        SECTIONS.add(new Section("Londres: Rafael Ramos", 1, "http://feeds.feedburner.com/lavanguardia/diariodelondres"));
        SECTIONS.add(new Section("La Habana: Fernando García", 1, "http://feeds.feedburner.com/lavanguardia/diariodelahabana"));
        SECTIONS.add(new Section("Berlín: Rafael Poch", 1, "http://feeds.feedburner.com/lavanguardia/diariodeberlin"));
        SECTIONS.add(new Section("Washington: Marc Bassets", 1, "http://feeds.feedburner.com/lavanguardia/diariodewashington"));
        SECTIONS.add(new Section("Diario itinerante: Andy Robinson", 1, "http://feeds.feedburner.com/lavanguardia/diarioitinerante"));
        SECTIONS.add(new Section("Roma: Eusebio Val", 1, "http://feeds.feedburner.com/lavanguardia/diarioderoma"));
        SECTIONS.add(new Section("Jerusalén: Henrique Cymerman", 1, "http://feeds.feedburner.com/lavanguardia/diariodejerusalen-cymerman"));

        SECTIONS.add(new Section("Blogs Actualidad", 0, null));
        SECTIONS.add(new Section("No digas que se te mueren las plantas", 1, "http://feeds.feedburner.com/lavanguardia/plantas"));
        SECTIONS.add(new Section("Perspectiva europea", 1, "http://feeds.feedburner.com/lavanguardia/perspectivaeuropea"));
        SECTIONS.add(new Section("Patologías urbanas", 1, "http://feeds.feedburner.com/lavanguardia/patologiasurbanas"));
        SECTIONS.add(new Section("El Dardo", 1, "http://feeds.feedburner.com/lavanguardia/eldardo"));
        SECTIONS.add(new Section("Entre sexos", 1, "http://feeds.feedburner.com/lavanguardia/entresexos"));
        SECTIONS.add(new Section("In saecula saeculorum", 1, "http://feeds.feedburner.com/lavanguardia/insaeculasaeculorum"));
        SECTIONS.add(new Section("Por quién doblan las esquinas", 1, "http://feeds.feedburner.com/lavanguardia/doblanesquinas"));
        SECTIONS.add(new Section("La Libreta", 1, "http://feeds.feedburner.com/lavanguardia/lalibreta"));
        SECTIONS.add(new Section("El Hilo", 1, "http://feeds.feedburner.com/lavanguardia/elhilo"));
        SECTIONS.add(new Section("Metamorfosis", 1, "http://feeds.feedburner.com/lavanguardia/metamorfosis"));
        SECTIONS.add(new Section("Vía pública", 1, "http://feeds.feedburner.com/lavanguardia/viapublica"));
        SECTIONS.add(new Section("Així tot va bé", 1, "http://feeds.feedburner.com/lavanguardia/totvabe"));
        SECTIONS.add(new Section("Pasos perdidos", 1, "http://feeds.feedburner.com/lavanguardia/pasosperdidos"));
        SECTIONS.add(new Section("El otro escaño", 1, "http://feeds.feedburner.com/lavanguardia/elotroescano"));
        SECTIONS.add(new Section("Villa y Cortes", 1, "http://feeds.feedburner.com/lavanguardia/villaycortes"));
        SECTIONS.add(new Section("Valencia", 1, "http://feeds.feedburner.com/lavanguardia/diariodevalencia"));
        SECTIONS.add(new Section("Desorientado en Oriente", 1, "http://feeds.feedburner.com/lavanguardia/desorientadoenoriente"));
        SECTIONS.add(new Section("Octavillas 2.0", 1, "http://feeds.feedburner.com/lavanguardia/octavillas"));
        SECTIONS.add(new Section("El zoom", 1, "http://feeds.feedburner.com/lavanguardia/elzoom"));
        SECTIONS.add(new Section("Valor añadido", 1, "http://feeds.feedburner.com/lavanguardia/valoranadido"));
        SECTIONS.add(new Section("Guerreros del teclado", 1, "http://feeds.feedburner.com/lavanguardia/guerrerosdelteclado"));

        SECTIONS.add(new Section("Blogs Gente", 0, null));
        SECTIONS.add(new Section("Ailof", 1, "http://feeds.feedburner.com/lavanguardia/ailof"));
        SECTIONS.add(new Section("Retwitteando", 1, "http://feeds.feedburner.com/lavanguardia/retwitteando"));
        SECTIONS.add(new Section("Qué Llevas", 1, "http://feeds.feedburner.com/lavanguardia/quellevas"));

        SECTIONS.add(new Section("Blogs Tecnología", 0, null));
        SECTIONS.add(new Section("La Cafetera Rusa", 1, "http://feeds.feedburner.com/lavanguardia/lacafeterarusa"));
        SECTIONS.add(new Section("El Cuarto Bit", 1, "http://feeds.feedburner.com/lavanguardia/elcuartobit"));
        SECTIONS.add(new Section("The Fourth Bit", 1, "http://feeds.feedburner.com/lavanguardia/thefourthbit"));
        SECTIONS.add(new Section("Teclado Móvil", 1, "http://feeds.feedburner.com/lavanguardia/tecladomovil"));

        SECTIONS.add(new Section("Blogs Cultura", 0, null));
        SECTIONS.add(new Section("Punto de lectura", 1, "http://feeds.feedburner.com/lavanguardia/puntodelectura"));
        SECTIONS.add(new Section("El arquero", 1, "http://feeds.feedburner.com/lavanguardia/elarquero"));
        SECTIONS.add(new Section("Jam Session", 1, "http://feeds.feedburner.com/lavanguardia/jamsession"));
        SECTIONS.add(new Section("Ctrl+Alt+Supr", 1, "http://feeds.feedburner.com/lavanguardia/ctrlaltsupr"));
        SECTIONS.add(new Section("Sin subtítulos", 1, "http://feeds.feedburner.com/lavanguardia/sinsubtitulos"));
        SECTIONS.add(new Section("Infografía", 1, "http://feeds.feedburner.com/lavanguardia/retwitteando"));
        SECTIONS.add(new Section("Cum Laude", 1, "http://feeds.feedburner.com/lavanguardia/cumlaude"));
        SECTIONS.add(new Section("Barcelona gratis", 1, "http://feeds.feedburner.com/lavanguardia/barcelonagratis"));

        SECTIONS.add(new Section("Blogs Deportes", 0, null));
        SECTIONS.add(new Section("Planeta Mar", 1, "http://feeds.feedburner.com/lavanguardia/planetamar"));
        SECTIONS.add(new Section("Pase en profundidad", 1, "http://feeds.feedburner.com/lavanguardia/paseenprofundidad"));
        SECTIONS.add(new Section("ÑBA", 1, "http://feeds.feedburner.com/lavanguardia/nba"));

        SECTIONS.add(new Section("Blogs Ficción y Humor", 0, null));
        SECTIONS.add(new Section("El último mono", 1, "http://feeds.feedburner.com/lavanguardia/elultimomono"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        int idash = news.description.indexOf("- ");
        if (idash != -1) news.description = news.description.substring(idash + 2);
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        try {
            org.jsoup.nodes.Document doc = getDocument(news.link);
            if (doc == null) return news;

            doc.select("script").remove();

            org.jsoup.select.Elements e = doc.select(".text,.video,.foto,.story-text");

            if (!e.isEmpty()) {
                e.select(".colB").remove();
                news.content = e.outerHtml();
            } else {
                e = doc.select(".entry-content");
                if (!e.isEmpty()) {
                    news.content = e.html();
                } else {
                    System.out.println("NO SE ENCUENTRA EL CONTENIDO: " + news.link);
                    System.out.println(doc.html());
                }
            }
        } catch (Exception e) {
            debug("[ERROR] link:" + news.link);
            e.printStackTrace();
        }
        return news;
    }

}
