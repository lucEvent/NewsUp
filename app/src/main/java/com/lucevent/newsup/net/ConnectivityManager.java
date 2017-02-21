package com.lucevent.newsup.net;

import android.content.Context;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

public class ConnectivityManager {

    private android.net.ConnectivityManager connectivityManager;

    public ConnectivityManager(@NonNull Context context)
    {
        connectivityManager = (android.net.ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isInternetAvailable()
    {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

}
