package com.newsup.net.dev;

import com.newsup.kernel.Section;

public class SectionDeprecated extends Section {

    public final String link;

    public SectionDeprecated(String name, int level, String link) {
        super(name, level);
        this.link = link;
    }
}
