package com.lucevent.newsup.kernel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TableRow;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.sports.Sports;
import com.lucevent.newsup.data.sports.util.LeagueTable;
import com.lucevent.newsup.data.sports.util.Sport;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.io.SDManager;
import com.lucevent.newsup.net.SportsReader;

import java.util.ArrayList;

public class SportsManager {

    private Context context;
    private Handler handler;

    /**
     * Managers
     **/
    protected static SDManager sdmanager;

    /**
     * Static variables
     **/
    private static ConnectivityManager connectivityManager;

    public SportsManager(Context context)
    {
        this(context, null);
    }

    public SportsManager(Context context, @Nullable Handler handler)
    {
        this.context = context;
        this.handler = handler;

        if (sdmanager == null)
            sdmanager = new SDManager(context);

        if (connectivityManager == null)
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (AppData.sports == null)
            AppData.sports = new Sports(context.getResources().getStringArray(R.array.sports));

    }

    public void getContent(Sport sport, Section section)
    {
        if (isInternetAvailable())
            new SportLoader(sport, section).get();
        else
            handler.obtainMessage(AppCode.NO_INTERNET, null).sendToTarget();
    }

    private class SportLoader {

        private final Sport sport;
        private final Section section;

        SportLoader(Sport sport, Section section)
        {
            this.sport = sport;
            this.section = section;
        }

        void get()
        {
            new Thread(task_get_content).start();
        }

        private Runnable task_get_content = new Runnable() {

            @Override
            public void run()
            {
                LeagueTable leagueTable = SportsReader.getLeagueTable(sport.code,
                        section != null ? sport.sections.indexOf(section) : 1);

                ArrayList<TableRow> tableRows = SportsReader.convertLeagueTable(context, leagueTable);

                handler.obtainMessage(AppCode.SPORT_LEAGUE_TABLE, tableRows).sendToTarget();
            }

        };
    }

    private boolean isInternetAvailable()
    {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

}
