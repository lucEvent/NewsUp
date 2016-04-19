package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class ElMundoSections extends Sections {

    public ElMundoSections()
    {
        super();

        add(new Section("Portada", "http://estaticos.elmundo.es/elmundo/rss/portada.xml", 0));
        add(new Section("España", "http://estaticos.elmundo.es/elmundo/rss/espana.xml", 0));
        add(new Section("Internacional", "http://estaticos.elmundo.es/elmundo/rss/internacional.xml", 0));
        add(new Section("Economía", "http://estaticos.elmundo.es/elmundo/rss/economia.xml", 0));
        add(new Section("Cultura", "http://estaticos.elmundo.es/elmundo/rss/cultura.xml", 0));
        add(new Section("Ciencia", "http://estaticos.elmundo.es/elmundo/rss/ciencia.xml", 0));

        add(new Section("Ciudades", null, -1));
        add(new Section("Alicante", "http://estaticos.elmundo.es/elmundo/rss/alicante.xml", 1));
        add(new Section("Castellón", "http://estaticos.elmundo.es/elmundo/rss/castellon.xml", 1));
        add(new Section("Madrid", "http://estaticos.elmundo.es/elmundo/rss/madrid.xml", 1));
        add(new Section("Málaga", "http://estaticos.elmundo.es/elmundo/rss/andalucia_malaga.xml", 1));
        add(new Section("Sevilla", "http://estaticos.elmundo.es/elmundo/rss/andalucia_sevilla.xml", 1));
        add(new Section("Valencia", "http://estaticos.elmundo.es/elmundo/rss/valencia.xml", 1));

        add(new Section("Regiones", null, -1));
        add(new Section("Andalucía", "http://estaticos.elmundo.es/elmundo/rss/andalucia.xml", 1));
        add(new Section("País vasco", "http://estaticos.elmundo.es/elmundo/rss/paisvasco.xml", 1));

        add(new Section("Solidaridad", "http://estaticos.elmundo.es/elmundo/rss/solidaridad.xml", 0));
        add(new Section("Comunicación", "http://estaticos.elmundo.es/elmundo/rss/comunicacion.xml", 0));
        add(new Section("Televisión", "http://estaticos.elmundo.es/elmundo/rss/television.xml", 0));
        add(new Section("Su vivienda", "http://estaticos.elmundo.es/elmundo/rss/suvivienda.xml", 0));
        add(new Section("Salud", "http://estaticos.elmundo.es/elmundosalud/rss/portada.xml", 0));

        add(new Section("Deportes", "http://estaticos.elmundo.es/elmundodeporte/rss/portada.xml", 0));
        add(new Section("Fútbol", "http://estaticos.elmundo.es/elmundodeporte/rss/futbol.xml", 1));
        add(new Section("Baloncesto", "http://estaticos.elmundo.es/elmundodeporte/rss/baloncesto.xml", 1));
        add(new Section("Ciclismo", "http://estaticos.elmundo.es/elmundodeporte/rss/ciclismo.xml", 1));
        add(new Section("Tenis", "http://estaticos.elmundo.es/elmundodeporte/rss/tenis.xml", 1));

        add(new Section("Yo dona", "http://estaticos.elmundo.es/yodona/rss/portada.xml", 0));

    }

}

