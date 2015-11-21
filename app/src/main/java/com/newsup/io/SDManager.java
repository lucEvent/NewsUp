package com.newsup.io;

import android.content.Context;
import android.os.Environment;

import com.newsup.kernel.News;
import com.newsup.kernel.Site;
import com.newsup.kernel.util.Compressor;
import com.newsup.settings.AppSettings;
import com.newsup.settings.SiteSettings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SDManager {

    private Context context;

    public SDManager(Context context) {
        this.context = context;
    }

    public News readNews(News news) {
        String filename = "n" + news.id;

        try {
            FileInputStream inputStream = context.openFileInput(filename);

            news.content = Compressor.decompress(inputStream);

            inputStream.close();
        } catch (FileNotFoundException e) {
            debug("[NO SE HA ENCONTRADO LA NOTICIA EN DISCO] news.id: " + news.id + " ##");
        } catch (Exception e) {
            debug("[Error en SDManager.readNews] news.id: " + news.id + " ##");
            e.printStackTrace();
        }
        return news;
    }

    public void saveNews(News news) {
        String filename = "n" + news.id;
        try {
            FileOutputStream outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);

            Compressor.compress(news.content, outputStream);

            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            debug("Error en SAVENEWS" + news.id + "## " + news.title);
            e.printStackTrace();
        }
    }

    public SiteSettings readSettingsOf(Site site) {
        SiteSettings result = new SiteSettings(site.code);

        String filename = "s" + site.code;
        try {
            ObjectInputStream oinputStream = new ObjectInputStream(context.openFileInput(filename));

            int nitems = oinputStream.readInt();
            result.sectionsOnMain = new boolean[nitems];
            result.sectionsToSave = new boolean[nitems];
            for (int i = 0; i < nitems; ++i) {
                result.sectionsOnMain[i] = oinputStream.readBoolean();
                result.sectionsToSave[i] = oinputStream.readBoolean();
            }
            oinputStream.close();

        } catch (Exception e) {
            int size = site.getSections().size();
            result.sectionsOnMain = new boolean[size];
            result.sectionsToSave = new boolean[size];
            result.sectionsOnMain[0] = true;
            result.sectionsToSave[0] = true;
            for (int i = 1; i < size; ++i) {
                result.sectionsOnMain[i] = false;
                result.sectionsToSave[i] = false;
            }
        }

        return result;
    }

    public void saveSettings(SiteSettings settings) {
        String filename = "s" + settings.sitecode;

        try {
            ObjectOutputStream ooutputStream = new ObjectOutputStream(context.openFileOutput(filename, Context.MODE_PRIVATE));

            ooutputStream.writeInt(settings.sectionsOnMain.length);
            for (int i = 0; i < settings.sectionsOnMain.length; ++i) {
                ooutputStream.writeBoolean(settings.sectionsOnMain[i]);
                ooutputStream.writeBoolean(settings.sectionsToSave[i]);
            }
            ooutputStream.close();
        } catch (Exception e) {
            debug("Error en SAVESETTINGS(SITE)");
            e.printStackTrace();
        }
    }


    public AppSettings readSettings() {
        AppSettings result = new AppSettings();

        try {
            ObjectInputStream in = new ObjectInputStream(context.openFileInput("sapp"));

            int nitems = in.readInt();
            result.MAIN_CODES = new int[nitems];
            for (int i = 0; i < nitems; ++i) result.MAIN_CODES[i] = in.readInt();

            nitems = in.readInt();
            result.FAV_CODES = new ArrayList<Integer>();
            for (int i = 0; i < nitems; ++i) result.FAV_CODES.add(in.readInt());

            in.close();

        } catch (Exception e) {
            // Nothing went wrong, it's just the setting up
        }
        return result;
    }

    public void saveSettings(AppSettings settings) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(context.openFileOutput("sapp", Context.MODE_PRIVATE));

            out.writeInt(settings.MAIN_CODES.length);
            for (int value : settings.MAIN_CODES) out.writeInt(value);

            out.writeInt(settings.FAV_CODES.size());
            for (int value : settings.FAV_CODES) out.writeInt(value);

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void debug(String text) {
        android.util.Log.d("##SDManager##", text);
    }

    public static File getDirectory() {
        File directory;
        if (Environment.getExternalStorageState() == null) {
            directory = new File(Environment.getDataDirectory() + "/NewsUp/");
        } else {
            directory = new File(Environment.getExternalStorageDirectory() + "/NewsUp/");
        }
        if (!directory.exists()) {
            directory.mkdir();
        }
        return directory;
    }

    public long getCacheSize() {
        long dbsize = context.getDatabasePath(Database.DATABASE_NAME).length();
        long size = 0;
        File[] files = context.getFilesDir().listFiles();
        for (File f : files) size += f.length();
        return size + dbsize;
    }

    public void wipeData() {
        File[] files = context.getFilesDir().listFiles();
        for (File f : files) f.delete();
    }
}