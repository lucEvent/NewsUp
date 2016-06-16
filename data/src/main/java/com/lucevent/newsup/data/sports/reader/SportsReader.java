package com.lucevent.newsup.data.sports.reader;

import com.lucevent.newsup.data.sports.util.LeagueTable;
import com.lucevent.newsup.data.util.Section;

public abstract class SportsReader {

    public abstract LeagueTable getLeagueTable(Section s);

}
