package com.lucevent.newsup.data.sports.reader;

import com.lucevent.newsup.data.sports.util.LeagueTable;
import com.lucevent.newsup.data.sports.util.LeagueTableRow;
import com.lucevent.newsup.data.util.Section;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Football extends SportsReader {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36";

    public Football()
    {
    }

    @Override
    public LeagueTable getLeagueTable(Section s)
    {
        Document doc;
        try {
            doc = org.jsoup.Jsoup.connect(s.url).userAgent(USER_AGENT).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        doc.select("script").remove();

        LeagueTable leagueTable = new LeagueTable(20);

        Elements htmltable = doc.select("#col-clasificacion .cmp,#col-clasificacion .impar");

        for (Element tr : htmltable) {

            Elements tds = tr.children();

            LeagueTableRow row = new LeagueTableRow();
            row.badgeUrl = tds.get(1).child(0).attr("src").replace("?size=37x&5", "");
            row.teamName = tds.get(1).text();
            row.points = tds.get(2).text();
            row.games = tds.get(3).text();
            row.won = tds.get(4).text();
            row.draw = tds.get(5).text();
            row.lost = tds.get(6).text();
            row.scored = tds.get(7).text();
            row.received = tds.get(8).text();
            leagueTable.add(row);

        }
        return leagueTable;
    }

}
