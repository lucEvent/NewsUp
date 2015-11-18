package com.newsup.kernel.section;

import com.newsup.kernel.Section;

import java.util.ArrayList;

public class GoteborgsPostenSections extends ArrayList<Section> {

    public GoteborgsPostenSections() {
        super();

        add(new Section("Nyheter", 0));
        add(new Section("Hela nyhetsdygnet", 0));

        add(new Section("Göteborg", 0));
        add(new Section("Bohuslän", 0));
        add(new Section("Halland", 0));
        add(new Section("Sverige", 0));
        add(new Section("Världen", 0));

        add(new Section("Ledare", 0));
        add(new Section("Debatt", 0));

        add(new Section("Sport", 0));
        add(new Section("Fotboll", 1));
        add(new Section("Handboll", 1));
        add(new Section("Ishockey", 1));

        add(new Section("Kultur & Nöje", 0));
        add(new Section("Ekonomi", 0));
        add(new Section("Konsument", 0));
        add(new Section("Bostad", 0));
        add(new Section("Resor", 0));
        add(new Section("Motor", 0));
        add(new Section("Mat & Dryck", 0));
        add(new Section("Litteraturrecensioner", 0));

    }

}
