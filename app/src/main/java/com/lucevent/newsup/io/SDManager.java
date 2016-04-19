package com.lucevent.newsup.io;

import android.content.Context;
import android.os.Environment;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.kernel.util.Compressor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SDManager {

    private Context context;

    public SDManager(Context context)
    {
        this.context = context;
    }

    public News readNews(News news)
    {
        String filename = "n" + news.id;
        System.out.println("reading News en SDMAngager: "+news.title);
        try {
            FileInputStream inputStream = context.openFileInput(filename);

            news.content = Compressor.decompress(inputStream);

            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("[SDM] NO SE HA ENCONTRADO LA NOTICIA EN DISCO [news.id: " + news.id + "] ##");
        } catch (Exception e) {
            System.out.println("[SDM] Error en SDManager.readNews [ news.id: " + news.id + "] ##");
            e.printStackTrace();
        }
        return news;
    }

    public void saveNews(News news)
    {
        String filename = "n" + news.id;
        try {
            FileOutputStream outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);

            Compressor.compress(news.content, outputStream);

            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            System.out.println("[SDM] Error en SAVENEWS [news.id = " + news.id + "] ## [title: " + news.title + "]");
            e.printStackTrace();
        }
    }

    public static File getDirectory()
    {
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

    public long getCacheSize()
    {
        long dbsize = context.getDatabasePath(Database.DATABASE_NAME).length();
        long size = 0;
        File[] files = context.getFilesDir().listFiles();
        for (File f : files) size += f.length();
        return size + dbsize;
    }

    public void wipeData()
    {
        File[] files = context.getFilesDir().listFiles();
        for (File f : files) f.delete();
    }

}
