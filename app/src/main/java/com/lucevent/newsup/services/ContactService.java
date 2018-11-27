package com.lucevent.newsup.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;

import com.lucevent.newsup.R;
import com.lucevent.newsup.kernel.AppCode;
import com.lucevent.newsup.net.BackendNames;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class ContactService extends Service implements BackendNames {

	private final IBinder binder = new Binder();

	public class Binder extends android.os.Binder {
		public ContactService getService()
		{
			return ContactService.this;
		}
	}

	private ConnectivityManager connectivityManager;

	@Override
	public void onCreate()
	{
		super.onCreate();
		connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return binder;
	}

	public boolean isInternetAvailable()
	{
		NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
		return ni != null && ni.isConnected() && ni.isAvailable();
	}

	public void sendMessage(final Handler handler, final String email, final String message)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				try {

					Connection.Response response = Jsoup.connect(MAIN_APP_SERVER + "?report")
							.method(Connection.Method.POST)
							.postDataCharset("UTF-8")
							.data("version", getString(R.string.app_version), "email", email, "message", message)
							.execute();

					handler.obtainMessage(response.statusCode() == 200 ? AppCode.REPORT_SEND_OK : AppCode.ERROR).sendToTarget();

				} catch (Exception ignored) {
				}
			}
		}).start();
	}

}


