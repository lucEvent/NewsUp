package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class GoteborgsPostenSections extends Sections {

    public GoteborgsPostenSections()
    {
        super();

        add(new Section("Nyheter", "http://www.gp.se/1.970150", 0));
        add(new Section("Hela nyhetsdygnet", "http://www.gp.se/1.165654", 0));

        add(new Section("Göteborg", "http://www.gp.se/1.16942", 0));
        add(new Section("Bohuslän", "http://www.gp.se/1.215341", 0));
        add(new Section("Halland", "http://www.gp.se/1.291999", 0));
        add(new Section("Sverige", "http://www.gp.se/1.16943", 0));
        add(new Section("Världen", "http://www.gp.se/1.16944", 0));

        add(new Section("Ledare", "http://www.gp.se/1.872491", 0));
        add(new Section("Debatt", "http://www.gp.se/1.315001", 0));

        add(new Section("Sport", "http://www.gp.se/1.16542", 0));
        add(new Section("Fotboll", "http://www.gp.se/1.2185578", 1));
        add(new Section("Handboll", "http://www.gp.se/1.2185611", 1));
        add(new Section("Ishockey", "http://www.gp.se/1.2185476", 1));

        add(new Section("Kultur & Nöje", "http://www.gp.se/1.16941", 0));
        add(new Section("Ekonomi", "http://www.gp.se/1.16555", 0));
        add(new Section("Konsument", "http://www.gp.se/1.16558", 0));
        add(new Section("Bostad", "http://www.gp.se/1.16562", 0));
        add(new Section("Resor", "http://www.gp.se/1.16569", 0));
        add(new Section("Motor", "http://www.gp.se/1.16570", 0));
        add(new Section("Mat & Dryck", "http://www.gp.se/1.16571", 0));
        add(new Section("Litteraturrecensioner", "http://www.gp.se/1.4465", 0));

    }

}
