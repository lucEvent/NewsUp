package com.lucevent.newsup.net;

import android.content.Context;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

public class ConnectivityManager {

	private android.net.ConnectivityManager mConnectivityManager;

	public ConnectivityManager(@NonNull Context context)
	{
		mConnectivityManager = (android.net.ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	public boolean isInternetAvailable()
	{
		NetworkInfo activeNetwork = mConnectivityManager.getActiveNetworkInfo();
		return activeNetwork != null && activeNetwork.isConnected();
	}

	public boolean isOnWifi()
	{
		return mConnectivityManager
				.getNetworkInfo(android.net.ConnectivityManager.TYPE_WIFI)
				.isConnectedOrConnecting();
	}

	public boolean isOnMobileData()
	{
		return mConnectivityManager
				.getNetworkInfo(android.net.ConnectivityManager.TYPE_MOBILE)
				.isConnectedOrConnecting();
	}

}
