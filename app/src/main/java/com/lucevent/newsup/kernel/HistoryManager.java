package com.lucevent.newsup.kernel;

import android.content.Context;
import android.os.Handler;

import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.io.DBManager;

public class HistoryManager {

	private final Handler handler;

	private DBManager dbmanager;

	public HistoryManager(Context context, Handler handler)
	{
		this.handler = handler;

		dbmanager = new DBManager(context);
	}

	public void getReadNews()
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				NewsMap news = dbmanager.readReadNews();
				handler.obtainMessage(AppCode.NEWS_COLLECTION, news.values()).sendToTarget();
			}
		}).start();
	}

	public DBManager getDataManager()
	{
		return dbmanager;
	}

	public void clearHistory()
	{
		dbmanager.deleteHistory();
	}

}
