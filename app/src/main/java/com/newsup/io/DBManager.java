package com.newsup.io;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.newsup.io.Database.DBHistoryNews;
import com.newsup.io.Database.DBNews;
import com.newsup.kernel.basic.HistoryNews;
import com.newsup.kernel.basic.News;
import com.newsup.kernel.basic.Site;
import com.newsup.kernel.set.NewsMap;
import com.newsup.kernel.set.Tags;

public class DBManager {

    private Database db;

    public DBManager(Context context) {
        db = new Database(context);
    }

    /**
     * ******** READS *************
     **/
    public NewsMap readNews(Site site) {
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
        debug("[#] NEWS IN " + site.name + " DATABASE: " + result.size());
        return result;
    }

    public News readNews(int id) {
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

    public NewsMap readHistoryNews() {
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
        debug("[#] NEWS IN HISTORY DATABASE: " + result.size());
        return result;
    }


    /**
     * ******** UPDATES *************
     **/
    public void updateNews(News news) {
        ContentValues values = new ContentValues();
        values.put(DBNews.title, news.title);
        values.put(DBNews.link, news.link);
        values.put(DBNews.date, news.date);
        values.put(DBNews.description, news.description);
        values.put(DBNews.tags, news.categories.toString());

        SQLiteDatabase database = db.getWritableDatabase();
        database.update(DBNews.db, values, DBNews.id + " = " + news.id, null);
        database.close();
    }

    /**
     * *********************** INSERTS ************************
     **/
    public void insertNews(News news) {
        ContentValues values = new ContentValues();
        values.put(DBNews.site_code, news.site_code);
        values.put(DBNews.title, news.title);
        values.put(DBNews.link, news.link);
        values.put(DBNews.date, news.date);
        values.put(DBNews.description, news.description);
        values.put(DBNews.tags, news.categories.toString());

        SQLiteDatabase database = db.getWritableDatabase();
        news.id = (int) database.insert(DBNews.db, null, values);
        database.close();
    }


    public void insertHistoryNews(News news) {
        ContentValues values = new ContentValues();
        values.put(DBHistoryNews.news_id, news.id);
        values.put(DBHistoryNews.site_code, news.site_code);
        values.put(DBHistoryNews.title, news.title);
        values.put(DBHistoryNews.link, news.link);
        values.put(DBHistoryNews.date, news.date);
        values.put(DBHistoryNews.description, news.description);
        values.put(DBHistoryNews.tags, news.categories.toString());

        SQLiteDatabase database = db.getWritableDatabase();
        database.insert(DBHistoryNews.db, null, values);
        database.close();
    }

    /**
     * ******************** DELETES *********************
     **/
    public void deleteNews(News news) {
        SQLiteDatabase database = db.getWritableDatabase();
        database.delete(DBNews.db, DBNews.id + " = " + news.id, null);
        database.close();
    }

    public void deleteHistory() {
        SQLiteDatabase database = db.getWritableDatabase();
        database.delete(DBHistoryNews.db, null, null);
        database.close();
    }

    public void wipeData() {
        SQLiteDatabase database = db.getWritableDatabase();
        database.delete(DBNews.db, null, null);
        database.delete(DBHistoryNews.db, null, null);
        database.close();
    }

    /**
     * Cursor conversions
     */
    private News cursorToNews(Cursor c) {
        return new News(c.getInt(0), c.getString(2), c.getString(3), c.getString(5),
                c.getLong(4), new Tags(c.getString(6)));
    }


    private void debug(String text) {
        android.util.Log.d("##DBManager##", text);
    }


}
