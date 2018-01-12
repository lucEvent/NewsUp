package com.lucevent.newsup.io;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.Database.DBDownloadSchedule;
import com.lucevent.newsup.io.Database.DBNews;
import com.lucevent.newsup.io.Database.DBNote;
import com.lucevent.newsup.io.Database.DBReadings;
import com.lucevent.newsup.kernel.util.Note;
import com.lucevent.newsup.kernel.util.Notes;
import com.lucevent.newsup.services.util.Download;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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

            if (cursor.moveToFirst())
                do {
                    News news = DBNews.parse(cursor);
                    result.put(news.id, news);
                }
                while (cursor.moveToNext());

            cursor.close();
            database.close();
        }
        AppSettings.printlog("[DB] NEWS IN " + site.name + " DATABASE: " + result.size());
        return result;
    }

    public NewsMap readNews(Site site, int[] section_codes)
    {
        return readNews(site.code, section_codes);
    }

    public NewsMap readNews(int site_code, int[] section_codes)
    {
        NewsMap res = new NewsMap();

        synchronized (this) {
            SQLiteDatabase database = db.getReadableDatabase();

            StringBuilder sb_where = new StringBuilder(DBNews.site_code + "=" + site_code + " AND (");
            sb_where.append("(" + DBNews.section_code + "=").append(section_codes[0]).append(")");
            for (int i = 1; i < section_codes.length; i++)
                sb_where.append(" OR (" + DBNews.section_code + "=").append(section_codes[i]).append(")");
            sb_where.append(")");

            Cursor cursor = database.query(DBNews.db, DBNews.cols, sb_where.toString(), null, null, null, null);
            if (cursor.moveToFirst())
                do {

                    News news = DBNews.parse(cursor);
                    res.put(news.id, news);

                } while (cursor.moveToNext());

            cursor.close();
            database.close();
        }
        AppSettings.printlog("[DB] NEWS IN [" + site_code + "] DATABASE (just some sections): " + res.size());
        return res;
    }

    public News readNews(int id)
    {
        News news = null;

        synchronized (this) {
            SQLiteDatabase database = db.getReadableDatabase();
            Cursor cursor = database.query(DBNews.db, DBNews.cols, Database.id + "=" + id, null, null, null, null);

            if (cursor.moveToFirst())
                news = DBNews.parse(cursor);

            cursor.close();
            database.close();
        }
        return news;
    }

    public NewsMap readReadNews()
    {
        NewsMap result = new NewsMap();

        synchronized (this) {
            SQLiteDatabase database = db.getReadableDatabase();
            Cursor cursor = database.query(DBNews.db, DBNews.cols, DBNews.read_on + ">0", null, null, null, null);

            if (cursor.moveToFirst())
                do {

                    News news = DBNews.parse(cursor);
                    result.put(news.id, news);

                } while (cursor.moveToNext());

            cursor.close();
            database.close();
        }

        AppSettings.printlog("[DB] NEWS READ IN DATABASE: " + result.size());
        return result;
    }

    public ArrayList<Download> readDownloadSchedules()
    {
        ArrayList<Download> result = new ArrayList<>();

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

    public ArrayList<Pair<Integer, Integer>> readSyncReadings()
    {
        ArrayList<Pair<Integer, Integer>> result = new ArrayList<>();

        synchronized (this) {
            SQLiteDatabase database = db.getReadableDatabase();
            Cursor cursor = database.query(DBReadings.db, DBReadings.cols, null, null, null, null, null);

            if (cursor.moveToFirst())
                do {

                    result.add(DBReadings.parseSync(cursor));

                } while (cursor.moveToNext());

            cursor.close();
            database.close();
        }
        return result;
    }

    public void readReadings(Sites sites)
    {
        synchronized (this) {
            SQLiteDatabase database = db.getReadableDatabase();
            Cursor cursor = database.query(DBReadings.db, DBReadings.cols, null, null, null, null, null);

            if (cursor.moveToFirst())
                do {

                    Pair<Integer, Integer> data = DBReadings.parseAll(cursor);

                    sites.getSiteByCode(data.first).setNumReadings(data.second);

                } while (cursor.moveToNext());

            cursor.close();
            database.close();
        }
    }

    public Notes readNotes()
    {
        Notes result = new Notes();

        synchronized (this) {
            SQLiteDatabase database = db.getReadableDatabase();
            Cursor cursor = database.query(DBNote.db, DBNote.cols, null, null, null, null, null);

            if (cursor.moveToFirst())
                do {

                    result.add(DBNote.parse(cursor));

                } while (cursor.moveToNext());

            cursor.close();
            database.close();
        }
        return result;
    }

    public boolean contains(News news)
    {
        boolean r;
        synchronized (this) {
            SQLiteDatabase database = db.getReadableDatabase();
            Cursor cursor = database.query(DBNews.db, DBNews.cols, Database.id + "=" + news.id, null, null, null, null);

            r = cursor.moveToFirst();

            cursor.close();
            database.close();
        }
        return r;
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
        values.put(DBNews.site_code, news.site_code);
        values.put(DBNews.section_code, news.section_code);
        values.put(DBNews.read_on, news.readOn);

        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            database.update(DBNews.db, values, Database.id + " = " + news.id, null);
            database.close();
        }
    }

    public void updateDownloadSchedule(Download schedule)
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

    /**
     * *********************** INSERTS ************************
     **/
    public void insertNews(News news)
    {
        ContentValues values = new ContentValues();
        values.put(Database.id, news.id);
        values.put(DBNews.site_code, news.site_code);
        values.put(DBNews.title, news.title);
        values.put(DBNews.link, news.link);
        values.put(DBNews.date, news.date);
        values.put(DBNews.description, news.description);
        values.put(DBNews.tags, news.tags.toString());
        values.put(DBNews.section_code, news.section_code);
        values.put(DBNews.read_on, 0);

        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            database.insert(DBNews.db, null, values);
            database.close();
        }
    }

    public void setNewsRead(News news)
    {
        news.readOn = System.currentTimeMillis();

        ContentValues values = new ContentValues();
        values.put(DBNews.read_on, news.readOn);

        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            database.update(DBNews.db, values, Database.id + " = " + news.id, null);
            values.clear();

            Cursor cursor = database.query(DBReadings.db, DBReadings.cols,
                    DBReadings.site_code + "=" + news.site_code, null, null, null, null);

            if (cursor.moveToFirst()) {
                database.execSQL(
                        "UPDATE " + DBReadings.db +
                                " SET " + DBReadings.readings + "=" + DBReadings.readings + "+1," +
                                DBReadings.readings_since_last_sync + "=" + DBReadings.readings_since_last_sync + "+1" +
                                " WHERE " + DBReadings.site_code + "=" + news.site_code);
            } else {
                values.put(DBReadings.site_code, news.site_code);
                values.put(DBReadings.readings, 1);
                values.put(DBReadings.readings_since_last_sync, 1);
                database.insert(DBReadings.db, null, values);
            }
            cursor.close();
            database.close();
        }
    }

    public Download insertDownloadSchedule(int hour, int minute, boolean notify,
                                           boolean repeat, boolean[] days, int[] sites_codes)
    {
        int id = new Random().nextInt();
        id = id < 0 ? -id : id;
        return insertDownloadScheduleSpec(id, hour, minute, notify, repeat, days, sites_codes);
    }

    public Download insertDownloadScheduleSpec(int id, int hour, int minute, boolean notify,
                                               boolean repeat, boolean[] days, int[] sites_codes)
    {
        ContentValues values = new ContentValues();
        values.put(Database.id, id);
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

        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            database.insert(DBDownloadSchedule.db, null, values);
            database.close();
        }
        return new Download(id, hour, minute, notify, repeat, days, sites_codes);
    }

    public Note insertNote(String note)
    {
        ContentValues values = new ContentValues();
        values.put(DBNote.note, note);

        int id;
        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            id = (int) database.insert(DBNote.db, null, values);
            database.close();
        }
        return new Note(id, note);
    }

    /**
     * ******************** DELETES *********************
     **/
    public int[] deleteNewsSince(long timeBound)
    {
        int[] ids;
        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            Cursor cursor = database.query(DBNews.db, new String[]{Database.id}, DBNews.date + "<" + timeBound, null, null, null, null);

            ids = new int[cursor.getCount()];
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                ids[i] = cursor.getInt(0);
            }
            cursor.close();

            database.delete(DBNews.db, DBNews.date + "<" + timeBound, null);
            database.close();
        }
        AppSettings.printlog("[DB] " + ids.length + " news deleted from DB");
        return ids;
    }

    public void deleteHistory()
    {
        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();

            ContentValues values = new ContentValues(1);
            values.put(DBNews.read_on, 0);
            database.update(DBNews.db, values, null, null);

            database.close();
            database.close();
        }
    }

    public void deleteDownloadSchedule(Download schedule)
    {
        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            database.delete(DBDownloadSchedule.db, Database.id + " = " + schedule.id, null);
            database.close();
        }
    }

    public void clearSyncReadings()
    {
        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();

            ContentValues values = new ContentValues(1);
            values.put(DBReadings.readings_since_last_sync, 0);
            database.update(DBReadings.db, values, null, null);

            database.close();
        }
    }

    public void deleteNote(int note_id)
    {
        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            database.delete(DBNote.db, Database.id + "=" + note_id, null);
            database.close();
        }
    }

    public void wipeData()
    {
        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            database.delete(DBNews.db, null, null);
            database.close();
        }
    }

}
