package com.lucevent.newsup.io;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.Database.DBDownloadSchedule;
import com.lucevent.newsup.io.Database.DBHistoryNews;
import com.lucevent.newsup.io.Database.DBNews;
import com.lucevent.newsup.io.Database.DBReadingStats;
import com.lucevent.newsup.services.util.DownloadSchedule;

import java.util.ArrayList;
import java.util.Arrays;

public class DBManager {

    private Database db;

    public DBManager(Context context)
    {
        db = new Database(context);
    }

    public NewsMap readNews(Site site)
    {
        NewsMap result = new NewsMap();

        synchronized (this) {
            SQLiteDatabase database = db.getReadableDatabase();
            Cursor cursor = database.query(DBNews.db, DBNews.cols, DBNews.site_code + "=" + site.code, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                News news = DBNews.parse(cursor);
                news.site_code = site.code;
                result.add(news);

                cursor.moveToNext();
            }
            cursor.close();
            database.close();
        }
        AppSettings.printlog("[DB] NEWS IN " + site.name + " DATABASE: " + result.size());
        return result;
    }

    public News readNews(int id)
    {
        News news = null;

        synchronized (this) {
            SQLiteDatabase database = db.getReadableDatabase();
            Cursor cursor = database.query(DBNews.db, DBNews.cols, Database.id + "=" + id, null, null, null, null);

            if (cursor.moveToFirst()) {

                news = DBNews.parse(cursor);
                news.site_code = cursor.getInt(1);

            }
            cursor.close();
            database.close();
        }
        return news;
    }

    public NewsMap readHistoryNews()
    {
        NewsMap result = new NewsMap();

        synchronized (this) {
            SQLiteDatabase database = db.getReadableDatabase();
            Cursor cursor = database.query(DBHistoryNews.db, DBHistoryNews.cols, null, null, null, null, null);

            if (cursor.moveToFirst())
                do {

                    result.add(DBHistoryNews.parse(cursor));

                } while (cursor.moveToNext());

            cursor.close();
            database.close();
        }

        AppSettings.printlog("[DB] NEWS IN HISTORY DATABASE: " + result.size());
        return result;
    }

    public ArrayList<Pair<Integer, Integer>> readReadingStats()
    {
        ArrayList<Pair<Integer, Integer>> result = new ArrayList<>();

        synchronized (this) {
            SQLiteDatabase database = db.getReadableDatabase();
            Cursor cursor = database.query(DBReadingStats.db, DBReadingStats.cols, null, null, null, null, null);

            if (cursor.moveToFirst())
                do {

                    result.add(DBReadingStats.parse(cursor));

                } while (cursor.moveToNext());

            cursor.close();
            database.close();
        }
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

        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            database.update(DBNews.db, values, Database.id + " = " + news.id, null);
            database.close();
        }
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

        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            news.id = (int) database.insert(DBNews.db, null, values);
            database.close();
        }
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

        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            database.insert(DBHistoryNews.db, null, values);
            values.clear();

            Cursor cursor = database.query(DBReadingStats.db, DBReadingStats.cols,
                    DBReadingStats.site_code + "=" + news.site_code, null, null, null, null);

            if (cursor.moveToFirst()) {
                Pair<Integer, Integer> readingStat = DBReadingStats.parse(cursor);
                values.put(DBReadingStats.readings, readingStat.second + 1);
                database.update(DBReadingStats.db, values, DBReadingStats.site_code + "=" + news.site_code, null);
            } else {
                values.put(DBReadingStats.site_code, news.site_code);
                values.put(DBReadingStats.readings, 1);
                database.insert(DBReadingStats.db, null, values);
            }
            cursor.close();
            database.close();
        }
    }

    /**
     * ******************** DELETES *********************
     **/
    public NewsArray deleteOldNews(long timeBound)
    {
        NewsArray result = new NewsArray();

        synchronized (this) {
            SQLiteDatabase database = db.getReadableDatabase();
            Cursor cursor = database.query(DBNews.db, DBNews.cols, DBNews.date + "<" + timeBound, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                News news = DBNews.parse(cursor);
                result.add(news);

                cursor.moveToNext();
            }
            cursor.close();
            database.delete(DBHistoryNews.db, DBHistoryNews.date + "<" + timeBound, null);
            database.delete(DBNews.db, DBNews.date + "<" + timeBound, null);
            database.close();
        }
        AppSettings.printlog("[DB] " + result.size() + " old news deleted from DB");
        return result;
    }

    public void deleteHistory()
    {
        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            database.delete(DBHistoryNews.db, null, null);
            database.close();
        }
    }

    public void deleteReadingStats()
    {
        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            database.delete(DBReadingStats.db, null, null);
            database.close();
        }
    }

    public void wipeData()
    {
        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            database.delete(DBNews.db, null, null);
            database.delete(DBHistoryNews.db, null, null);
            database.close();
        }
    }

    /**
     * DownloadSchedule methods
     */
    public ArrayList<DownloadSchedule> readDownloadSchedules()
    {
        ArrayList<DownloadSchedule> result = new ArrayList<>();

        synchronized (this) {
            SQLiteDatabase database = db.getReadableDatabase();
            Cursor cursor = database.query(DBDownloadSchedule.db, DBDownloadSchedule.cols, null, null, null, null, null);

            if (cursor.moveToFirst())
                do {

                    result.add(DBDownloadSchedule.parse(cursor));
                }
                while (cursor.moveToNext());

            cursor.close();
            database.close();
        }
        AppSettings.printlog("[DB] DownloadSchedule in database: " + result.size());
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

        int id;
        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            id = (int) database.insert(DBDownloadSchedule.db, null, values);
            database.close();
        }
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

        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            database.update(DBDownloadSchedule.db, values, Database.id + " = " + schedule.id, null);
            database.close();
        }
    }

    public void deleteDownloadSchedule(DownloadSchedule schedule)
    {
        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            database.delete(DBDownloadSchedule.db, Database.id + " = " + schedule.id, null);
            database.close();
        }
    }

}
