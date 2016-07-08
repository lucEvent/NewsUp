package com.lucevent.newsup.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.sports.util.LeagueTable;
import com.lucevent.newsup.data.sports.util.LeagueTableRow;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SportsReader {

    private static final String query = "http://newsup-2406.appspot.com/app?sports&code=";

    public static LeagueTable getLeagueTable(int sport_code, int isection)
    {
        Document doc = null;
        try {
            doc = Jsoup.connect(query + sport_code + "," + isection).get();
        } catch (IOException e) {
            AppSettings.printerror("[SR] Problem fetching:" + query + sport_code + "," + isection, e);
        }
        if (doc == null) return null;

        Element htmlTable = doc.select("leaguetable").get(0);

        LeagueTable leagueTable = new LeagueTable(Integer.parseInt(htmlTable.attr("n")));

        for (Element htmlRow : htmlTable.select("row")) {

            LeagueTableRow row = new LeagueTableRow();
            row.unWrap(htmlRow.text());
            leagueTable.add(row);

        }
        return leagueTable;
    }

    public static ArrayList<TableRow> convertLeagueTable(Context context, LeagueTable leagueTable)
    {
        ArrayList<TableRow> res = new ArrayList<>(21);

        LayoutInflater inflater = LayoutInflater.from(context);

        res.add((TableRow) inflater.inflate(R.layout.i_league_table_header, null, false));

        int p = 1;
        for (LeagueTableRow row : leagueTable) {
            TableRow tableRow = (TableRow) inflater.inflate(R.layout.i_league_table_row, null, false);
            if ((p & 1) > 0)
                tableRow.setBackgroundColor(0x88dddddd);

            ((TextView) tableRow.findViewById(R.id.position)).setText("" + p++);
            ((ImageView) tableRow.findViewById(R.id.badge)).setImageBitmap(bitmapFromUrl(row.badgeUrl));
            ((TextView) tableRow.findViewById(R.id.teamName)).setText(row.teamName);
            ((TextView) tableRow.findViewById(R.id.points)).setText(row.points);
            ((TextView) tableRow.findViewById(R.id.games)).setText(row.games);
            ((TextView) tableRow.findViewById(R.id.won)).setText(row.won);
            ((TextView) tableRow.findViewById(R.id.draw)).setText(row.draw);
            ((TextView) tableRow.findViewById(R.id.lost)).setText(row.lost);
            ((TextView) tableRow.findViewById(R.id.scored)).setText(row.scored);
            ((TextView) tableRow.findViewById(R.id.received)).setText(row.received);

            res.add(tableRow);
        }
        return res;
    }

    public static Bitmap bitmapFromUrl(String url)
    {
        Bitmap res = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            res = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            AppSettings.printerror("Error finding " + url, e);
        }
        return res;
    }

}
