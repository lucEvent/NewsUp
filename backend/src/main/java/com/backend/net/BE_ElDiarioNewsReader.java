package com.backend.net;

import com.backend.kernel.BE_News;
import com.backend.kernel.BE_Section;
import com.backend.kernel.list.BE_Sections;

public class BE_ElDiarioNewsReader extends BE_NewsReader {

    public BE_ElDiarioNewsReader() {
        super();

        SECTIONS = new BE_Sections();
        SECTIONS.add(new BE_Section("Últimas noticias", "http://eldiario.es.feedsportal.com/rss"));
        SECTIONS.add(new BE_Section("Desalambre (Derechos Humanos)", "http://www.eldiario.es/rss/desalambre/"));
        SECTIONS.add(new BE_Section("Cultura", "http://www.eldiario.es/rss/cultura/"));
        SECTIONS.add(new BE_Section("Opinión", "http://www.eldiario.es/rss/opinion/"));
        SECTIONS.add(new BE_Section("Andalucía", "http://eldiario.es.feedsportal.com/andalucia/rss"));
        SECTIONS.add(new BE_Section("Euskadi", "http://eldiario.es.feedsportal.com/norte/rss"));
        SECTIONS.add(new BE_Section("Catalunya", "http://eldiario.es.feedsportal.com/catalunya/rss"));
        SECTIONS.add(new BE_Section("Catalunya Plural", "http://www.eldiario.es/rss/catalunyaplural/"));
        SECTIONS.add(new BE_Section("Galicia", "http://eldiario.es.feedsportal.com/galicia/rss"));
        SECTIONS.add(new BE_Section("Canarias", "http://www.eldiario.es/rss/canariasahora/"));

        SECTIONS.add(new BE_Section("Desalambre (Derechos Humanos)", "http://www.eldiario.es/rss/section/10593/"));
        SECTIONS.add(new BE_Section("Juegoreviews", "http://www.eldiario.es/rss/section/11709/"));
        SECTIONS.add(new BE_Section("Política", "http://www.eldiario.es/rss/section/1000/"));
        SECTIONS.add(new BE_Section("Economía", "http://www.eldiario.es/rss/section/3000/"));
        SECTIONS.add(new BE_Section("Sociedad", "http://www.eldiario.es/rss/section/2000/"));
        SECTIONS.add(new BE_Section("Internacional", "http://www.eldiario.es/rss/section/4000/"));
        SECTIONS.add(new BE_Section("Cultura", "http://www.eldiario.es/rss/section/10838/"));
        SECTIONS.add(new BE_Section("Agenda publica", "http://www.eldiario.es/rss/section/10038/"));

        SECTIONS.add(new BE_Section("Regiones", null));
        SECTIONS.add(new BE_Section("Andalucía", "http://www.eldiario.es/rss/section/10279/"));
        SECTIONS.add(new BE_Section("Euskadi", "http://www.eldiario.es/rss/section/10418/"));
        SECTIONS.add(new BE_Section("Catalunya", "http://www.eldiario.es/rss/section/10098/"));
        SECTIONS.add(new BE_Section("Catalunya Plural", "http://www.eldiario.es/rss/section/10553/"));
        SECTIONS.add(new BE_Section("Galicia", "http://www.eldiario.es/rss/section/10048/"));
        SECTIONS.add(new BE_Section("Canarias", "http://www.eldiario.es/rss/section/10624/"));

        SECTIONS.add(new BE_Section("Autores", null));
        SECTIONS.add(new BE_Section("Ignacio Escolar", "http://www.eldiario.es/rss/section/20002/"));
        SECTIONS.add(new BE_Section("Iñigo Saez de Ugarte", "http://www.eldiario.es/rss/section/20003/"));
        SECTIONS.add(new BE_Section("Juan Luis Sánchez", "http://www.eldiario.es/rss/section/20001/"));
        SECTIONS.add(new BE_Section("Belén Carreño", "http://www.eldiario.es/rss/section/20032/"));
        SECTIONS.add(new BE_Section("Ana Requena", "http://www.eldiario.es/rss/section/20006/"));
        SECTIONS.add(new BE_Section("Natalia Chientaroli", "http://www.eldiario.es/rss/section/20033/"));
        SECTIONS.add(new BE_Section("Marta Peirano", "http://www.eldiario.es/rss/section/20033/"));
        SECTIONS.add(new BE_Section("Marilín Gonzalo", "http://www.eldiario.es/rss/section/10291/"));
        SECTIONS.add(new BE_Section("Aitor Riveiro", "http://www.eldiario.es/rss/section/10362/"));
        SECTIONS.add(new BE_Section("Gonzalo Cortizo", "http://www.eldiario.es/rss/section/20007/"));
        SECTIONS.add(new BE_Section("Irene Castro", "http://www.eldiario.es/rss/section/10173/"));
        SECTIONS.add(new BE_Section("Pedro Ãgueda", "http://www.eldiario.es/rss/section/10861/"));
        SECTIONS.add(new BE_Section("Gonzalo Boyé", "http://www.eldiario.es/rss/section/10374/"));
        SECTIONS.add(new BE_Section("Rosa María Artal", "http://www.eldiario.es/rss/section/20011/"));
        SECTIONS.add(new BE_Section("Ruth Toledano", "http://www.eldiario.es/rss/section/10018/"));
        SECTIONS.add(new BE_Section("Olga Rodríguez", "http://www.eldiario.es/rss/section/20012/"));
        SECTIONS.add(new BE_Section("Isaac Rosa", "http://www.eldiario.es/rss/section/20017/"));
        SECTIONS.add(new BE_Section("Ramón Lobo", "http://www.eldiario.es/rss/section/12162/"));
        SECTIONS.add(new BE_Section("David Bravo", "http://www.eldiario.es/rss/section/20049/"));
        SECTIONS.add(new BE_Section("Maruja Torres", "http://www.eldiario.es/rss/section/10892/"));
        SECTIONS.add(new BE_Section("Carlos Elordi", "http://www.eldiario.es/rss/section/20013/"));

        SECTIONS.add(new BE_Section("Blogs", null));
        SECTIONS.add(new BE_Section("Escolar.net", "http://eldiario.es.feedsportal.com/escolar/rss"));
        SECTIONS.add(new BE_Section("+Pikara", "http://www.eldiario.es/rss/section/11334/"));
        SECTIONS.add(new BE_Section("Carta con pregunta", "http://www.eldiario.es/rss/section/11898/"));
        SECTIONS.add(new BE_Section("Ciencia Crítica", "http://www.eldiario.es/rss/section/10262/"));
        SECTIONS.add(new BE_Section("Contrapoder", "http://www.eldiario.es/rss/section/11069/"));
        SECTIONS.add(new BE_Section("De retrones y hombres", "http://www.eldiario.es/rss/section/10207/"));
        SECTIONS.add(new BE_Section("Desde mi bici", "http://www.eldiario.es/rss/section/11910/"));
        SECTIONS.add(new BE_Section("El caballo de Nietzsche", "http://www.eldiario.es/rss/section/10550/"));
        SECTIONS.add(new BE_Section("El Rastreador", "http://www.eldiario.es/rss/section/10252/"));
        SECTIONS.add(new BE_Section("Euroblog", "http://www.eldiario.es/rss/section/11912/"));
        SECTIONS.add(new BE_Section("Interferencias", "http://www.eldiario.es/rss/section/10007/"));
        SECTIONS.add(new BE_Section("La crispación", "http://www.eldiario.es/rss/section/10046/"));
        SECTIONS.add(new BE_Section("La Meseta", "http://www.eldiario.es/rss/section/10253/"));
        SECTIONS.add(new BE_Section("Las Crónicas de Asturias", "http://www.eldiario.es/rss/section/10771/"));
        SECTIONS.add(new BE_Section("Líbero", "http://www.eldiario.es/rss/section/10065/"));
        SECTIONS.add(new BE_Section("Micromachismos", "http://www.eldiario.es/rss/section/10865/"));
        SECTIONS.add(new BE_Section("Mientras tanto... en internet", "http://www.eldiario.es/rss/section/12136/"));
        SECTIONS.add(new BE_Section("Seriéfilos", "http://www.eldiario.es/rss/section/11673/"));
        SECTIONS.add(new BE_Section("Tipos Inquietantes", "http://www.eldiario.es/rss/section/12247/"));
        SECTIONS.add(new BE_Section("Zona Crítica", "http://www.eldiario.es/rss/section/8002/"));
        SECTIONS.add(new BE_Section("¡Protesto señoría!", "http://www.eldiario.es/rss/section/10233/"));
        SECTIONS.add(new BE_Section("Última llamada", "http://www.eldiario.es/rss/section/12200/"));
        SECTIONS.add(new BE_Section("El Defensor de la Comunidad", "http://www.eldiario.es/rss/section/10014/"));
        SECTIONS.add(new BE_Section("El blog de la redacción", "http://www.eldiario.es/rss/section/10044/"));
        SECTIONS.add(new BE_Section("El blog de los socios", "http://www.eldiario.es/rss/section/10231/"));

    }

    @Override
    protected BE_News applySpecialCase(BE_News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(news.description);
        doc.select("a,img[width=\"1\"],br").remove();
        news.description = "";
        news.content = doc.select("body").html();
        return news;
    }

}
