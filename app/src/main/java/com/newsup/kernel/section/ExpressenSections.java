package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class ExpressenSections extends ArrayList<Section> {

    public ExpressenSections() {
        super();

        add(new Section("Nyheter", 0));
        add(new Section("VästSverige GT", 0));
        add(new Section("SydSverige KVP", 0));

        add(new Section("Sport", 0));
        add(new Section("Fotboll", 1));
        add(new Section("Hockey", 1));

        add(new Section("Nöjesnyheter", 0));
        add(new Section("Debatt", 0));
        add(new Section("Ledare", 0));
        add(new Section("Kultur", 0));
        add(new Section("Hälsa & Skönhet", 0));
        add(new Section("Leva & Bo", 0));
        add(new Section("Motor", 0));
        add(new Section("Res", 0));
        add(new Section("Dokument", 0));
        add(new Section("Extra", 0));

    }

}
