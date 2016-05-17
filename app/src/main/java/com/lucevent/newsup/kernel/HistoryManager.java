package com.lucevent.newsup.kernel;

import android.content.Context;
import android.os.Handler;

import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.io.DBManager;

public class HistoryManager {

    /**
     * Static constants
     **/
    private final Handler handler;

    /**
     * Controllers
     **/
    private DBManager dbmanager;

    public HistoryManager(Context context, Handler handler)
    {
        this.handler = handler;

        dbmanager = new DBManager(context);
    }

    public void getAppHistorial()
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                NewsMap news = dbmanager.readHistoryNews();
                handler.obtainMessage(AppCode.NEWS_MAP_READ, news).sendToTarget();
            }
        }).start();
    }

    public void clearHistory()
    {
        dbmanager.deleteHistory();
    }

}
