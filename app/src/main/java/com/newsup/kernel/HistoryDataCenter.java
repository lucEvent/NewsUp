package com.newsup.kernel;

import android.content.Context;
import android.os.Handler;

import com.newsup.io.DBManager;
import com.newsup.io.SDManager;
import com.newsup.kernel.basic.News;
import com.newsup.kernel.set.NewsMap;
import com.newsup.task.SocketMessage;

public class HistoryDataCenter {

    /**
     * Static constants
     **/
    private final Context context;
    private final Handler handler;

    /**
     * Controllers
     **/
    private DBManager dbmanager;
    private SDManager sdmanager;

    public HistoryDataCenter(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;

        dbmanager = new DBManager(context);
        sdmanager = new SDManager(context);

    }

    public void load_Historial() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                NewsMap news = dbmanager.readHistoryNews();
                handler.obtainMessage(SocketMessage.HISTORY_READ, news).sendToTarget();

            }
        }).start();
    }

    public News getNewsContent(News news) {
        if (news.content == null || news.content.isEmpty()) {
            sdmanager.readNews(news);
        }
        return news;
    }

    public void clearHistory() {
        dbmanager.deleteHistory();
    }

    private void debug(String text) {
        android.util.Log.d("##HistorialDataCenter##", text);
    }

}
