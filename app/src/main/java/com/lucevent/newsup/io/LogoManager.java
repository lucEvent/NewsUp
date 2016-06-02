package com.lucevent.newsup.io;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import com.lucevent.newsup.AppSettings;

import java.util.HashMap;

public class LogoManager {

    public enum Size {
        DRAWER, I_ITEM, ACTION_BAR
    }

    private static AssetManager dataManager;

    private static HashMap<Integer, Drawable> drawer_logos, i_item_logos, bar_logos;

    public static LogoManager getInstance(Context context, int size)
    {
        dataManager = context.getAssets();

        drawer_logos = new HashMap<>(size);
        i_item_logos = new HashMap<>(size);
        bar_logos = new HashMap<>(size);

        return new LogoManager();
    }

    public static Drawable getLogo(int site_code, LogoManager.Size size)
    {
        HashMap<Integer, Drawable> map;

        if (size == Size.DRAWER)
            map = drawer_logos;
        else if (size == Size.I_ITEM)
            map = i_item_logos;
        else if (size == Size.ACTION_BAR)
            map = bar_logos;
        else
            return null;

        Drawable res = map.get(site_code);
        if (res == null) {
            try {
                res = Drawable.createFromStream(dataManager.open(site_code + ".png"), null);
                map.put(site_code, res);
            } catch (Exception e) {
                AppSettings.printerror("[LM] [EXCEPTION] Couldn't read asset " + site_code + ".png");
            }
        }
        return res;
    }

}
