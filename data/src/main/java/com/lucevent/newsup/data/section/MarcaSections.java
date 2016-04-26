package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class MarcaSections extends Sections {

    public MarcaSections()
    {
        super();

        add(new Section("Notícias principales", "http://estaticos.marca.com/rss/portada.xml", 0));
        add(new Section("Fútbol", "http://estaticos.marca.com/rss/futbol.xml", 0));
        add(new Section("Baloncesto", "http://estaticos.marca.com/rss/baloncesto.xml", 0));
        add(new Section("Motor", "http://estaticos.marca.com/rss/motor.xml", 0));
        add(new Section("Tenis", "http://estaticos.marca.com/rss/tenis.xml", 0));
        add(new Section("Ciclismo", "http://estaticos.marca.com/rss/ciclismo.xml", 0));
        add(new Section("Golf", "http://estaticos.marca.com/rss/golf.xml", 0));
        add(new Section("Atletismo", "http://estaticos.marca.com/rss/atletismo.xml", 0));
        add(new Section("Balonmano", "http://estaticos.marca.com/rss/balonmano.xml", 0));

    }

}
