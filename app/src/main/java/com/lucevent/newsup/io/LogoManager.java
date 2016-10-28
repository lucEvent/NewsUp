package com.lucevent.newsup.io;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;

import com.lucevent.newsup.AppSettings;

public class LogoManager {

    public enum Size {
        DRAWER, I_ITEM, ACTION_BAR, SELECT_SCREEN
    }

    private static AssetManager dataManager;

    private static SparseArray<Drawable> drawer_logos, i_item_logos, bar_logos, select_screen_logos;

    public static LogoManager getInstance(Context context, int size)
    {
        dataManager = context.getAssets();

        drawer_logos = new SparseArray<>(size);
        i_item_logos = new SparseArray<>(size);
        bar_logos = new SparseArray<>(size);
        select_screen_logos = new SparseArray<>(size);

        return new LogoManager();
    }

    public static Drawable getLogo(int site_code, LogoManager.Size size)
    {
        SparseArray<Drawable> map;

        switch (size) {
            case DRAWER:
                map = drawer_logos;
                break;
            case I_ITEM:
                map = i_item_logos;
                break;
            case ACTION_BAR:
                map = bar_logos;
                break;
            case SELECT_SCREEN:
                map = select_screen_logos;
                break;
            default:
                return null;
        }

        Drawable res = map.get(site_code);
        if (res == null) {
            try {
                res = Drawable.createFromStream(dataManager.open(site_code + ".png"), null);
                map.append(site_code, res);
            } catch (Exception e) {
                AppSettings.printerror("[LM] [EXCEPTION] Couldn't read asset " + site_code + ".png", e);
            }
        }
        return res;
    }

    public static Bitmap getBitmap(int site_code, int appIconSize)
    {
        int fifth = appIconSize / 5;
        int visibleSize = 3 * fifth;

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(dataManager.open(site_code + ".png"));

            if (bitmap.getWidth() != visibleSize || bitmap.getHeight() != visibleSize)
                bitmap = Bitmap.createScaledBitmap(bitmap, visibleSize, visibleSize, true);
/*
            Paint bg = new Paint();
            bg.setAlpha(0);

            Bitmap res = Bitmap.createBitmap(appIconSize,appIconSize,null);
            Canvas c = new Canvas(res);
            c.drawRect(0, 0, appIconSize, appIconSize, bg);
            c.drawBitmap(bitmap, fifth, fifth, null);

            bitmap=res;
*/

        } catch (Exception e) {
            AppSettings.printerror("[LM] [EXCEPTION] Couldn't read asset " + site_code + ".png", e);
        }
        return bitmap;
    }

}
