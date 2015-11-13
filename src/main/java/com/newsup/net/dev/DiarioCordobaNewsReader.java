package com.newsup.net.dev;

import com.newsup.kernel.Section;
import com.newsup.kernel.list.SectionList;
import com.newsup.net.NewsReader;

public class DiarioCordobaNewsReader extends NewsReader {

    public DiarioCordobaNewsReader() {
        super();

        SECTIONS = new SectionList();
        SECTIONS.add(new Section("Portada", 0, "http://zetaestaticos.com/cordoba/rss/portada_es.xml"));
        SECTIONS.add(new Section("Última hora", 0, "http://zetaestaticos.com/cordoba/rss/ultimahora_es.xml"));
        SECTIONS.add(new Section("Alto Guadalquivir", 0, "http://zetaestaticos.com/cordoba/rss/233_es.xml"));
        SECTIONS.add(new Section("Andaluía", 0, "http://zetaestaticos.com/cordoba/rss/1_es.xml"));
        SECTIONS.add(new Section("Área Blanquiverde", 0, "http://zetaestaticos.com/cordoba/rss/8_es.xml"));
        SECTIONS.add(new Section("Atletismo", 0, "http://zetaestaticos.com/cordoba/rss/262_es.xml"));
        SECTIONS.add(new Section("Baloncesto", 0, "http://zetaestaticos.com/cordoba/rss/264_es.xml"));
        SECTIONS.add(new Section("Balonmano", 0, "http://zetaestaticos.com/cordoba/rss/263_es.xml"));
        SECTIONS.add(new Section("Cabra", 0, "http://zetaestaticos.com/cordoba/rss/234_es.xml"));
        SECTIONS.add(new Section("Cofradías", 0, "http://zetaestaticos.com/cordoba/rss/246_es.xml"));
        SECTIONS.add(new Section("Cultura", 0, "http://zetaestaticos.com/cordoba/rss/3_es.xml"));
        SECTIONS.add(new Section("Deportes", 0, "http://zetaestaticos.com/cordoba/rss/4_es.xml"));
        SECTIONS.add(new Section("Economía", 0, "http://zetaestaticos.com/cordoba/rss/5_es.xml"));
        SECTIONS.add(new Section("España", 0, "http://zetaestaticos.com/cordoba/rss/7_es.xml"));
        SECTIONS.add(new Section("Gente", 0, "http://zetaestaticos.com/cordoba/rss/204_es.xml"));
        SECTIONS.add(new Section("Hípica", 0, "http://zetaestaticos.com/cordoba/rss/242_es.xml"));
        SECTIONS.add(new Section("Internacional", 0, "http://zetaestaticos.com/cordoba/rss/6_es.xml"));
        SECTIONS.add(new Section("La Brimz X en Córdoba", 0, "http://zetaestaticos.com/cordoba/rss/256_es.xml"));
        SECTIONS.add(new Section("Local", 0, "http://zetaestaticos.com/cordoba/rss/101_es.xml"));
        SECTIONS.add(new Section("Lucena", 0, "http://zetaestaticos.com/cordoba/rss/235_es.xml"));
        SECTIONS.add(new Section("Montilla", 0, "http://zetaestaticos.com/cordoba/rss/236_es.xml"));
        SECTIONS.add(new Section("Olimpiadas Londres 2012", 0, "http://zetaestaticos.com/cordoba/rss/239_es.xml"));
        SECTIONS.add(new Section("Opinión", 0, "http://zetaestaticos.com/cordoba/rss/100_es.xml"));
        SECTIONS.add(new Section("Palma del Río", 0, "http://zetaestaticos.com/cordoba/rss/237_es.xml"));
        SECTIONS.add(new Section("Polideportivo", 0, "http://zetaestaticos.com/cordoba/rss/265_es.xml"));
        SECTIONS.add(new Section("Pozoblanco", 0, "http://zetaestaticos.com/cordoba/rss/238_es.xml"));
        SECTIONS.add(new Section("Provincia", 0, "http://zetaestaticos.com/cordoba/rss/102_es.xml"));
        SECTIONS.add(new Section("Sociedad", 0, "http://zetaestaticos.com/cordoba/rss/103_es.xml"));
        SECTIONS.add(new Section("Televisión", 0, "http://zetaestaticos.com/cordoba/rss/104_es.xml"));
        SECTIONS.add(new Section("Tema del día", 0, "http://zetaestaticos.com/cordoba/rss/106_es.xml"));
        SECTIONS.add(new Section("Tu Informas", 0, "http://zetaestaticos.com/cordoba/rss/221_es.xml"));
        SECTIONS.add(new Section("Turismo", 0, "http://zetaestaticos.com/cordoba/rss/251_es.xml"));
        SECTIONS.add(new Section("UCOniversitas", 0, "http://zetaestaticos.com/cordoba/rss/261_es.xml"));
        SECTIONS.add(new Section("Vida Sana", 0, "http://zetaestaticos.com/cordoba/rss/255_es.xml"));

    }

}