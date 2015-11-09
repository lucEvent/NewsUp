package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class ElDiarioNewsReader extends NewsReader {

    public ElDiarioNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Últimas noticias", 0, "http://eldiario.es.feedsportal.com/rss"));
        SECTIONS.add(new Section("Desalambre (Derechos Humanos)", 1, "http://www.eldiario.es/rss/desalambre/"));
        SECTIONS.add(new Section("Cultura", 1, "http://www.eldiario.es/rss/cultura/"));
        SECTIONS.add(new Section("Opinión", 1, "http://www.eldiario.es/rss/opinion/"));
        SECTIONS.add(new Section("Andalucía", 1, "http://eldiario.es.feedsportal.com/andalucia/rss"));
        SECTIONS.add(new Section("Euskadi", 1, "http://eldiario.es.feedsportal.com/norte/rss"));
        SECTIONS.add(new Section("Catalunya", 1, "http://eldiario.es.feedsportal.com/catalunya/rss"));
        SECTIONS.add(new Section("Catalunya Plural", 1, "http://www.eldiario.es/rss/catalunyaplural/"));
        SECTIONS.add(new Section("Galicia", 1, "http://eldiario.es.feedsportal.com/galicia/rss"));
        SECTIONS.add(new Section("Canarias", 1, "http://www.eldiario.es/rss/canariasahora/"));

        SECTIONS.add(new Section("Desalambre (Derechos Humanos)", 0, "http://www.eldiario.es/rss/section/10593/"));
        SECTIONS.add(new Section("Juegoreviews", 0, "http://www.eldiario.es/rss/section/11709/"));
        SECTIONS.add(new Section("Política", 0, "http://www.eldiario.es/rss/section/1000/"));
        SECTIONS.add(new Section("Economía", 0, "http://www.eldiario.es/rss/section/3000/"));
        SECTIONS.add(new Section("Sociedad", 0, "http://www.eldiario.es/rss/section/2000/"));
        SECTIONS.add(new Section("Internacional", 0, "http://www.eldiario.es/rss/section/4000/"));
        SECTIONS.add(new Section("Cultura", 0, "http://www.eldiario.es/rss/section/10838/"));
        SECTIONS.add(new Section("Agenda publica", 0, "http://www.eldiario.es/rss/section/10038/"));

        SECTIONS.add(new Section("Regiones", 0, null));
        SECTIONS.add(new Section("Andalucía", 1, "http://www.eldiario.es/rss/section/10279/"));
        SECTIONS.add(new Section("Euskadi", 1, "http://www.eldiario.es/rss/section/10418/"));
        SECTIONS.add(new Section("Catalunya", 1, "http://www.eldiario.es/rss/section/10098/"));
        SECTIONS.add(new Section("Catalunya Plural", 1, "http://www.eldiario.es/rss/section/10553/"));
        SECTIONS.add(new Section("Galicia", 1, "http://www.eldiario.es/rss/section/10048/"));
        SECTIONS.add(new Section("Canarias", 1, "http://www.eldiario.es/rss/section/10624/"));

        SECTIONS.add(new Section("Autores", 0, null));
        SECTIONS.add(new Section("Ignacio Escolar", 1, "http://www.eldiario.es/rss/section/20002/"));
        SECTIONS.add(new Section("Ïñigo Saez de Ugarte", 1, "http://www.eldiario.es/rss/section/20003/"));
        SECTIONS.add(new Section("Juan Luis Sánchez", 1, "http://www.eldiario.es/rss/section/20001/"));
        SECTIONS.add(new Section("Belén Carreño", 1, "http://www.eldiario.es/rss/section/20032/"));
        SECTIONS.add(new Section("Ana Requena", 1, "http://www.eldiario.es/rss/section/20006/"));
        SECTIONS.add(new Section("Natalia Chientaroli", 1, "http://www.eldiario.es/rss/section/20033/"));
        SECTIONS.add(new Section("Marta Peirano", 1, "http://www.eldiario.es/rss/section/20033/"));
        SECTIONS.add(new Section("Marilín Gonzalo", 1, "http://www.eldiario.es/rss/section/10291/"));
        SECTIONS.add(new Section("Aitor Riveiro", 1, "http://www.eldiario.es/rss/section/10362/"));
        SECTIONS.add(new Section("Gonzalo Cortizo", 1, "http://www.eldiario.es/rss/section/20007/"));
        SECTIONS.add(new Section("Irene Castro", 1, "http://www.eldiario.es/rss/section/10173/"));
        SECTIONS.add(new Section("Pedro Ãgueda", 1, "http://www.eldiario.es/rss/section/10861/"));
        SECTIONS.add(new Section("Gonzalo Boyé", 1, "http://www.eldiario.es/rss/section/10374/"));
        SECTIONS.add(new Section("Rosa María Artal", 1, "http://www.eldiario.es/rss/section/20011/"));
        SECTIONS.add(new Section("Ruth Toledano", 1, "http://www.eldiario.es/rss/section/10018/"));
        SECTIONS.add(new Section("Olga Rodríguez", 1, "http://www.eldiario.es/rss/section/20012/"));
        SECTIONS.add(new Section("Isaac Rosa", 1, "http://www.eldiario.es/rss/section/20017/"));
        SECTIONS.add(new Section("Ramón Lobo", 1, "http://www.eldiario.es/rss/section/12162/"));
        SECTIONS.add(new Section("David Bravo", 1, "http://www.eldiario.es/rss/section/20049/"));
        SECTIONS.add(new Section("Maruja Torres", 1, "http://www.eldiario.es/rss/section/10892/"));
        SECTIONS.add(new Section("Carlos Elordi", 1, "http://www.eldiario.es/rss/section/20013/"));

        SECTIONS.add(new Section("Blogs", 0, null));
        SECTIONS.add(new Section("Escolar.net", 1, "http://eldiario.es.feedsportal.com/escolar/rss"));
        SECTIONS.add(new Section("+Pikara", 1, "http://www.eldiario.es/rss/section/11334/"));
        SECTIONS.add(new Section("Carta con pregunta", 1, "http://www.eldiario.es/rss/section/11898/"));
        SECTIONS.add(new Section("Ciencia Crítica", 1, "http://www.eldiario.es/rss/section/10262/"));
        SECTIONS.add(new Section("Contrapoder", 1, "http://www.eldiario.es/rss/section/11069/"));
        SECTIONS.add(new Section("De retrones y hombres", 1, "http://www.eldiario.es/rss/section/10207/"));
        SECTIONS.add(new Section("Desde mi bici", 1, "http://www.eldiario.es/rss/section/11910/"));
        SECTIONS.add(new Section("El caballo de Nietzsche", 1, "http://www.eldiario.es/rss/section/10550/"));
        SECTIONS.add(new Section("El Rastreador", 1, "http://www.eldiario.es/rss/section/10252/"));
        SECTIONS.add(new Section("Euroblog", 1, "http://www.eldiario.es/rss/section/11912/"));
        SECTIONS.add(new Section("Interferencias", 1, "http://www.eldiario.es/rss/section/10007/"));
        SECTIONS.add(new Section("La crispación", 1, "http://www.eldiario.es/rss/section/10046/"));
        SECTIONS.add(new Section("La Meseta", 1, "http://www.eldiario.es/rss/section/10253/"));
        SECTIONS.add(new Section("Las Crónicas de Asturias", 1, "http://www.eldiario.es/rss/section/10771/"));
        SECTIONS.add(new Section("Líbero", 1, "http://www.eldiario.es/rss/section/10065/"));
        SECTIONS.add(new Section("Micromachismos", 1, "http://www.eldiario.es/rss/section/10865/"));
        SECTIONS.add(new Section("Mientras tanto... en internet", 1, "http://www.eldiario.es/rss/section/12136/"));
        SECTIONS.add(new Section("Seriéfilos", 1, "http://www.eldiario.es/rss/section/11673/"));
        SECTIONS.add(new Section("Tipos Inquietantes", 1, "http://www.eldiario.es/rss/section/12247/"));
        SECTIONS.add(new Section("Zona Crítica", 1, "http://www.eldiario.es/rss/section/8002/"));
        SECTIONS.add(new Section("¡Protesto señoría!", 1, "http://www.eldiario.es/rss/section/10233/"));
        SECTIONS.add(new Section("Última llamada", 1, "http://www.eldiario.es/rss/section/12200/"));
        SECTIONS.add(new Section("El Defensor de la Comunidad", 1, "http://www.eldiario.es/rss/section/10014/"));
        SECTIONS.add(new Section("El blog de la redacción", 1, "http://www.eldiario.es/rss/section/10044/"));
        SECTIONS.add(new Section("El blog de los socios", 1, "http://www.eldiario.es/rss/section/10231/"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(news.description);
        doc.select("a,img[width=\"1\"],br").remove();
        news.description = "";
        news.content = doc.select("body").html();
        return news;
    }

}
