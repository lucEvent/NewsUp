package com.lucevent.newsup.io;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.Tags;
import com.lucevent.newsup.io.Database.DBDownloadSchedule;
import com.lucevent.newsup.io.Database.DBHistoryNews;
import com.lucevent.newsup.io.Database.DBNews;
import com.lucevent.newsup.kernel.util.HistoryNews;
import com.lucevent.newsup.services.util.DownloadSchedule;

import java.util.ArrayList;
import java.util.Arrays;

public class DBManager {

    private Database db;

    public DBManager(Context context)
    {
        db = new Database(context);
    }

    /**
     * ******** READS *************
     **/
    public NewsMap readNews(Site site)
    {
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.query(DBNews.db, DBNews.cols, DBNews.site_code + "=" + site.code, null, null, null, null);

        NewsMap result = new NewsMap();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            News news = cursorToNews(cursor);
            news.site_code = site.code;
            result.add(news);

            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        System.out.println("[DB] NEWS IN " + site.name + " DATABASE: " + result.size());
        return result;
    }

    public News readNews(int id)
    {
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.query(DBNews.db, DBNews.cols, DBNews.id + "=" + id, null, null, null, null);

        News news = null;

        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {

            news = cursorToNews(cursor);
            news.site_code = cursor.getInt(1);

        }
        cursor.close();
        database.close();
        return news;
    }

    public NewsMap readHistoryNews()
    {
        NewsMap result = new NewsMap();

        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.query(DBHistoryNews.db, DBHistoryNews.cols, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            int news_id = cursor.getInt(1);
            int site_code = cursor.getInt(2);
            String title = cursor.getString(3);
            String link = cursor.getString(4);
            long date = cursor.getLong(5);
            String description = cursor.getString(6);
            Tags categories = new Tags(cursor.getString(7));

            HistoryNews news = new HistoryNews(id, news_id, title, link, description, date, categories, site_code);
            result.add(news);

            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        System.out.println("[DB] NEWS IN HISTORY DATABASE: " + result.size());
        return result;
    }


    /**
     * ******** UPDATES *************
     **/
    public void updateNews(News news)
    {
        ContentValues values = new ContentValues();
        values.put(DBNews.title, news.title);
        values.put(DBNews.link, news.link);
        values.put(DBNews.date, news.date);
        values.put(DBNews.description, news.description);
        values.put(DBNews.tags, news.tags.toString());

        SQLiteDatabase database = db.getWritableDatabase();
        database.update(DBNews.db, values, DBNews.id + " = " + news.id, null);
        database.close();
    }

    /**
     * *********************** INSERTS ************************
     **/
    public void insertNews(News news)
    {
        ContentValues values = new ContentValues();
        values.put(DBNews.site_code, news.site_code);
        values.put(DBNews.title, news.title);
        values.put(DBNews.link, news.link);
        values.put(DBNews.date, news.date);
        values.put(DBNews.description, news.description);
        values.put(DBNews.tags, news.tags.toString());

        SQLiteDatabase database = db.getWritableDatabase();
        news.id = (int) database.insert(DBNews.db, null, values);
        database.close();
    }

    public void insertHistoryNews(News news)
    {
        ContentValues values = new ContentValues();
        values.put(DBHistoryNews.news_id, news.id);
        values.put(DBHistoryNews.site_code, news.site_code);
        values.put(DBHistoryNews.title, news.title);
        values.put(DBHistoryNews.link, news.link);
        values.put(DBHistoryNews.date, news.date);
        values.put(DBHistoryNews.description, news.description);
        values.put(DBHistoryNews.tags, news.tags.toString());

        SQLiteDatabase database = db.getWritableDatabase();
        database.insert(DBHistoryNews.db, null, values);
        database.close();
    }

    /**
     * ******************** DELETES *********************
     **/
    public void deleteNews(News news)
    {
        SQLiteDatabase database = db.getWritableDatabase();
        database.delete(DBNews.db, DBNews.id + " = " + news.id, null);
        database.close();
    }

    public void deleteHistory()
    {
        SQLiteDatabase database = db.getWritableDatabase();
        database.delete(DBHistoryNews.db, null, null);
        database.close();
    }

    public void wipeData()
    {
        SQLiteDatabase database = db.getWritableDatabase();
        database.delete(DBNews.db, null, null);
        database.delete(DBHistoryNews.db, null, null);
        database.close();
    }

    /**
     * DownloadSchedule methods
     */
    public ArrayList<DownloadSchedule> readDownloadSchedules()
    {
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.query(DBDownloadSchedule.db, DBDownloadSchedule.cols, null, null, null, null, null);

        ArrayList<DownloadSchedule> result = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            DownloadSchedule schedule = new DownloadSchedule(cursor.getInt(0));
            schedule.hour = cursor.getInt(1);
            schedule.minute = cursor.getInt(2);
            schedule.notify = cursor.getInt(3) == 1;
            schedule.repeat = cursor.getInt(4) == 1;
            schedule.days = new boolean[7];
            for (int i = 0; i < schedule.days.length; ++i)
                schedule.days[i] = cursor.getInt(i + 5) == 1;

            String[] codes = cursor.getString(12).split(", ");
            schedule.sites_codes = new int[codes.length];
            for (int i = 0; i < schedule.sites_codes.length; i++)
                schedule.sites_codes[i] = Integer.parseInt(codes[i]);

            result.add(schedule);

            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        System.out.println("[DB] DownloadSchedule in database: " + result.size());
        return result;
    }

    public DownloadSchedule insertDownloadSchedule(int hour, int minute, boolean notify,
                                                   boolean repeat, boolean[] days, int[] sites_codes)
    {
        ContentValues values = new ContentValues();
        values.put(DBDownloadSchedule.hour, hour);
        values.put(DBDownloadSchedule.minute, minute);
        values.put(DBDownloadSchedule.notify, notify);
        values.put(DBDownloadSchedule.repeat, repeat);
        values.put(DBDownloadSchedule.day_monday, days[0]);
        values.put(DBDownloadSchedule.day_tuesday, days[1]);
        values.put(DBDownloadSchedule.day_wednesday, days[2]);
        values.put(DBDownloadSchedule.day_thursday, days[3]);
        values.put(DBDownloadSchedule.day_friday, days[4]);
        values.put(DBDownloadSchedule.day_saturday, days[5]);
        values.put(DBDownloadSchedule.day_sunday, days[6]);
        String sitesCodesString = Arrays.toString(sites_codes);
        values.put(DBDownloadSchedule.sites_codes, sitesCodesString.substring(1, sitesCodesString.length() - 1));

        SQLiteDatabase database = db.getWritableDatabase();
        int id = (int) database.insert(DBDownloadSchedule.db, null, values);
        database.close();

        return new DownloadSchedule(id, hour, minute, notify, repeat, days, sites_codes);
    }

    public void updateDownloadSchedule(DownloadSchedule schedule)
    {
        ContentValues values = new ContentValues();
        values.put(DBDownloadSchedule.hour, schedule.hour);
        values.put(DBDownloadSchedule.minute, schedule.minute);
        values.put(DBDownloadSchedule.notify, schedule.notify);
        values.put(DBDownloadSchedule.repeat, schedule.repeat);
        values.put(DBDownloadSchedule.day_monday, schedule.days[0]);
        values.put(DBDownloadSchedule.day_tuesday, schedule.days[1]);
        values.put(DBDownloadSchedule.day_wednesday, schedule.days[2]);
        values.put(DBDownloadSchedule.day_thursday, schedule.days[3]);
        values.put(DBDownloadSchedule.day_friday, schedule.days[4]);
        values.put(DBDownloadSchedule.day_saturday, schedule.days[5]);
        values.put(DBDownloadSchedule.day_sunday, schedule.days[6]);
        String sitesCodesString = Arrays.toString(schedule.sites_codes);
        values.put(DBDownloadSchedule.sites_codes, sitesCodesString.substring(1, sitesCodesString.length() - 1));

        SQLiteDatabase database = db.getWritableDatabase();
        database.update(DBDownloadSchedule.db, values, DBDownloadSchedule.id + " = " + schedule.id, null);
        database.close();
    }

    public void deleteDownloadSchedule(DownloadSchedule schedule)
    {
        SQLiteDatabase database = db.getWritableDatabase();
        database.delete(DBDownloadSchedule.db, DBDownloadSchedule.id + " = " + schedule.id, null);
        database.close();
    }

    /**
     * Cursor conversions
     */
    private News cursorToNews(Cursor c)
    {
        return new News(c.getInt(0), c.getString(2), c.getString(3), c.getString(5),
                c.getLong(4), new Tags(c.getString(6)));
    }

}
