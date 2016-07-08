package com.lucevent.newsup.kernel.util;

import android.content.Context;
import android.graphics.Typeface;

import com.lucevent.newsup.AppSettings;

import java.util.Hashtable;

public class Typefaces {

    private static final Hashtable<String, Typeface> cache = new Hashtable<>();

    public static Typeface get(Context c, String assetPath)
    {
        synchronized (cache) {
            if (!cache.containsKey(assetPath)) {
                try {
                    Typeface t = Typeface.createFromAsset(c.getAssets(), assetPath);
                    cache.put(assetPath, t);
                } catch (Exception e) {
                    AppSettings.printerror("[Tf] Could not get typeface '" + assetPath + "' because " + e.getMessage(), e);
                    return null;
                }
            }
            return cache.get(assetPath);
        }
    }

}
