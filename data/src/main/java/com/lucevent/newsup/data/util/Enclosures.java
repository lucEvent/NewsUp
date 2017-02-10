package com.lucevent.newsup.data.util;

import java.util.ArrayList;

public class Enclosures extends ArrayList<Enclosure> {

    public Enclosures()
    {
        super();
    }

    public boolean has(int type)
    {
        for (Enclosure e : this)
            if (e.type == type)
                return true;
        return false;
    }

}
