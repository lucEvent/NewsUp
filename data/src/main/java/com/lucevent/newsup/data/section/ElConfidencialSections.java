package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElConfidencialSections extends Sections {

    public ElConfidencialSections()
    {
        super();

        add(new Section("España", "http://rss.elconfidencial.com/espana/", 0));
        add(new Section("Mundo", "http://rss.elconfidencial.com/mundo/", 0));
        add(new Section("Comunicación", "http://rss.elconfidencial.com/comunicacion/", 0));
        add(new Section("Sociedad", "http://rss.elconfidencial.com/sociedad/", 0));

        add(new Section("Opinión", "http://rss.blogs.elconfidencial.com/", 0));
        add(new Section("A. Casado", "http://rss.blogs.elconfidencial.com/espana/al-grano/", 1));
        add(new Section("J.A. Zarzalejos", "http://rss.blogs.elconfidencial.com/espana/notebook/", 1));
        add(new Section("C. Sánchez", "http://rss.blogs.elconfidencial.com/espana/mientras-tanto/", 1));
        add(new Section("El confidente", "http://rss.blogs.elconfidencial.com/espana/el-confidente/", 1));

        add(new Section("Cotizalia", null, -1));
        add(new Section("Mercados", "http://rss.elconfidencial.com/mercados/", 1));
        add(new Section("Economía", "http://rss.elconfidencial.com/economia/", 1));
        add(new Section("Empresas", "http://rss.elconfidencial.com/empresas/", 1));
        add(new Section("Vivienda", "http://rss.elconfidencial.com/vivienda/", 1));

        add(new Section("Teknautas", "http://rss.elconfidencial.com/tecnologia/", 0));
        add(new Section("Aplicaciones", "http://rss.elconfidencial.com/tags/temas/apps-9337/", 1));
        add(new Section("Emprendedores", "http://rss.elconfidencial.com/tags/economia/emprendedores-4800/", 1));
        add(new Section("Internet", "http://rss.elconfidencial.com/tags/temas/internet-9342/", 1));
        add(new Section("Móviles", "http://rss.elconfidencial.com/tags/otros/moviles-8601/", 1));
        add(new Section("Redes sociales", "http://rss.elconfidencial.com/tags/otros/redes-sociales-6338/", 1));

        add(new Section("Deportes", "http://rss.elconfidencial.com/deportes/", 0));
        add(new Section("Fútbol", "http://rss.elconfidencial.com/deportes/futbol/", 1));
        add(new Section("Baloncesto", "http://rss.elconfidencial.com/deportes/baloncesto/", 1));
        add(new Section("Fórmula 1", "http://rss.elconfidencial.com/deportes/formula-1/", 1));
        add(new Section("Motociclismo", "http://rss.elconfidencial.com/deportes/motociclismo/", 1));
        add(new Section("Tenis", "http://rss.elconfidencial.com/deportes/tenis/", 1));
        add(new Section("Ciclismo", "http://rss.elconfidencial.com/deportes/ciclismo/", 1));
        add(new Section("Golf", "http://rss.elconfidencial.com/deportes/golf/", 1));
        add(new Section("Otros deportes", "http://rss.elconfidencial.com/deportes/otros-deportes/", 1));

        add(new Section("Alma, Corazón, Vida", "http://rss.elconfidencial.com/alma-corazon-vida/", 0));
        add(new Section("Alimentación", "http://rss.elconfidencial.com/tags/otros/alimentacion-5601/", 1));
        add(new Section("Bienestar", "http://rss.elconfidencial.com/tags/temas/bienestar-9331/", 1));
        add(new Section("Educación", "http://rss.elconfidencial.com/tags/otros/educacion-5346/", 1));
        add(new Section("Psicología", "http://rss.elconfidencial.com/tags/otros/psicologia-5455/", 1));
        add(new Section("Salud", "http://rss.elconfidencial.com/tags/otros/salud-6110/", 1));
        add(new Section("Sexualidad", "http://rss.elconfidencial.com/tags/temas/sexualidad-6986/", 1));
        add(new Section("Trabajo", "http://rss.elconfidencial.com/tags/economia/trabajo-5284/", 1));

        add(new Section("Cultura", "http://rss.elconfidencial.com/cultura/", 0));
        add(new Section("Libros", "http://rss.elconfidencial.com/tags/otros/libros-5344/", 1));
        add(new Section("Arte", "http://rss.elconfidencial.com/tags/otros/arte-6092/", 1));
        add(new Section("Cine", "http://rss.elconfidencial.com/tags/otros/cine-7354/", 1));
        add(new Section("Música", "http://rss.elconfidencial.com/tags/otros/musica-5272/", 1));

        add(new Section("Vanitatis", null, -1));
        add(new Section("Actualidad", "http://rss.vanitatis.elconfidencial.com/noticias/", 1));
        add(new Section("Tendencias", "http://rss.vanitatis.elconfidencial.com/estilo/", 1));
        add(new Section("Televisión", "http://rss.vanitatis.elconfidencial.com/television/", 1));
        add(new Section("Casas Reales", "http://rss.vanitatis.elconfidencial.com/casas-reales/", 1));
        add(new Section("Blogs", "http://rss.blogs.vanitatis.elconfidencial.com/", 1));

    }

}
