package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class PokemonGoSections extends Sections {

    public PokemonGoSections()
    {
        super();

        add(new Section("English", "http://pokemongolive.com/en/post", 0));
        add(new Section("Deutch", "http://pokemongolive.com/de/post", 0));
        add(new Section("Espa\u00F1ol", "http://pokemongolive.com/es/post", 0));
        add(new Section("Fran\u00E7ais", "http://pokemongolive.com/fr/post", 0));
        add(new Section("Italiano", "http://pokemongolive.com/it/post", 0));
        add(new Section("Japanese", "http://pokemongolive.com/ja/post", 0));
        add(new Section("Korean", "http://pokemongolive.com/ko/post", 0));

    }

}
