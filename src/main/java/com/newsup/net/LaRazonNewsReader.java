package com.newsup.net;

import com.newsup.kernel.News;
import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;

public class LaRazonNewsReader extends NewsReader {

    public LaRazonNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Portada", 0, "http://www.larazon.es/rss/portada.xml"));

        SECTIONS.add(new Section("España", 0, "http://www.larazon.es/rss/espana.xml"));
        SECTIONS.add(new Section("Andalucía", 1, "http://www.larazon.es/rss/local/andalucia.xml"));
        SECTIONS.add(new Section("Cataluña", 1, "http://www.larazon.es/rss/local/cataluna.xml"));
        SECTIONS.add(new Section("Castilla y León", 1, "http://www.larazon.es/rss/local/castilla-y-leon.xml"));
        SECTIONS.add(new Section("Comunidad Valenciana", 1, "http://www.larazon.es/rss/local/comunidad-valenciana.xml"));
        SECTIONS.add(new Section("Madrid", 1, "http://www.larazon.es/rss/local/madrid.xml"));

        SECTIONS.add(new Section("Deportes", 0, "http://www.larazon.es/rss/deportes.xml"));
        SECTIONS.add(new Section("Futbol", 1, "http://www.larazon.es/rss/deportes/futbol.xml"));
        SECTIONS.add(new Section("Baloncesto", 1, "http://www.larazon.es/rss/deportes/baloncesto.xml"));
        SECTIONS.add(new Section("Tenis", 1, "http://www.larazon.es/rss/deportes/tenis.xml"));
        SECTIONS.add(new Section("Golf", 1, "http://www.larazon.es/rss/deportes/golf.xml"));
        SECTIONS.add(new Section("Motociclismo", 1, "http://www.larazon.es/rss/deportes/motociclismo.xml"));
        SECTIONS.add(new Section("Formula1", 1, "http://www.larazon.es/rss/deportes/formula1.xml"));
        SECTIONS.add(new Section("Deportes", 1, "http://www.larazon.es/rss/deportes.xml"));
        SECTIONS.add(new Section("Ciclismo", 1, "http://www.larazon.es/rss/deportes/ciclismo.xml"));

        SECTIONS.add(new Section("Internacional", 0, "http://www.larazon.es/rss/internacional.xml"));
        SECTIONS.add(new Section("Economía", 0, "http://www.larazon.es/rss/economia.xml"));
        SECTIONS.add(new Section("Lifestyle", 0, "http://www.larazon.es/rss/lifestyle.xml"));
        SECTIONS.add(new Section("Religion", 0, "http://www.larazon.es/rss/religion.xml"));
        SECTIONS.add(new Section("Viajes", 0, "http://www.larazon.es/rss/viajes.xml"));
        SECTIONS.add(new Section("Verde", 0, "http://www.larazon.es/rss/verde.xml"));

        SECTIONS.add(new Section("Sociedad", 0, "http://www.larazon.es/rss/sociedad.xml"));
        SECTIONS.add(new Section("Ciencia", 1, "http://www.larazon.es/rss/sociedad/ciencia.xml"));
        SECTIONS.add(new Section("Comunicación", 1, "http://www.larazon.es/rss/sociedad/comunicacion.xml"));
        SECTIONS.add(new Section("Educación", 1, "http://www.larazon.es/rss/sociedad/educacion.xml"));
        SECTIONS.add(new Section("Medio Ambiente", 1, "http://www.larazon.es/rss/sociedad/medio-ambiente.xml"));
        SECTIONS.add(new Section("Tecnología", 1, "http://www.larazon.es/rss/sociedad/tecnologia.xml"));
        SECTIONS.add(new Section("Videojuegos", 1, "http://www.larazon.es/rss/sociedad/videojuegos.xml"));

        SECTIONS.add(new Section("Cultura", 0, "http://www.larazon.es/rss/cultura.xml"));
        SECTIONS.add(new Section("Arte", 1, "http://www.larazon.es/rss/cultura/arte.xml"));
        SECTIONS.add(new Section("Cine", 1, "http://www.larazon.es/rss/cultura/cine.xml"));
        SECTIONS.add(new Section("Gastronomia", 1, "http://www.larazon.es/rss/cultura/gastronomia.xml"));
        SECTIONS.add(new Section("Libros", 1, "http://www.larazon.es/rss/cultura/libros.xml"));
        SECTIONS.add(new Section("Música", 1, "http://www.larazon.es/rss/cultura/musica.xml"));
        SECTIONS.add(new Section("Teatro", 1, "http://www.larazon.es/rss/cultura/teatro.xml"));

    }

    @Override
    protected News applySpecialCase(News news, String content) {
        news.content = news.description;
        news.description = "";
        return news;
    }

    @Override
    public News readNewsContent(News news) {
        return news;
    }

}