package com.lucevent.newsup.io;

import android.content.Context;
import android.os.Environment;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.kernel.util.Compressor;

import java.io.BufferedInputStream;
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
        try {
            FileInputStream inputStream = context.openFileInput("n" + news.id);

            news.content = Compressor.decompress(inputStream);

            inputStream.close();
        } catch (FileNotFoundException e) {
            AppSettings.printerror("[SDM] Couldn't find news in storage [news.id: " + news.id + "]", e);
        } catch (Exception e) {
            AppSettings.printerror("[SDM] Error in SDManager.readNews [ news.id: " + news.id + "]", e);
        }
        return news;
    }

    public void saveNews(News news)
    {
        try {
            FileOutputStream outputStream = context.openFileOutput("n" + news.id, Context.MODE_PRIVATE);

            Compressor.compress(news.content, outputStream);

            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            AppSettings.printerror("[SDM] Error in saveNews [news.id = " + news.id + "] ## [title: " + news.title + "]", e);
        }
    }

    public void deleteNews(News news)
    {
        try {
            context.deleteFile("n" + news.id);
        } catch (Exception e) {
            AppSettings.printerror("[SDM] Error in deleteNews [news.id = " + news.id + "] ## [title: " + news.title + "]", e);
        }
    }

    public static File getDirectory()
    {
        File directory;
        if (Environment.getExternalStorageState() == null)
            directory = new File(Environment.getDataDirectory() + "/NewsUp/");
        else
            directory = new File(Environment.getExternalStorageDirectory() + "/NewsUp/");

        if (!directory.exists())
            directory.mkdir();

        return directory;
    }

    public static String readRaw(Context context, int id)
    {
        try {
            BufferedInputStream stream = new BufferedInputStream(context.getResources().openRawResource(id));

            byte[] bytes = new byte[4096];
            int bytesRead;
            StringBuilder sb = new StringBuilder();
            while ((bytesRead = stream.read(bytes, 0, bytes.length)) != -1)
                sb.append(new String(bytes, 0, bytesRead));

            return sb.toString();
        } catch (Exception e) {
            AppSettings.printerror("Error in readRaw", e);
            e.printStackTrace();
        }
        return "";
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
