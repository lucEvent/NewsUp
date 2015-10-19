package com.newsup.io;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.newsup.io.Database.DBNews;
import com.newsup.kernel.News;
import com.newsup.kernel.Site;
import com.newsup.kernel.list.NewsMap;
import com.newsup.kernel.list.Tags;
import com.newsup.kernel.util.Date;

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
        Cursor cursor = database.query(DBNews.db, DBNews.cols, DBNews.site_code + "=" + site.code, null, null, null, DBNews.id + " ASC");

        NewsMap result = new NewsMap();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(2);
            String link = cursor.getString(3);
            String description = cursor.getString(5);
            Date date = new Date(cursor.getLong(4));
            Tags categories = new Tags(cursor.getString(6));

            News news = new News(id, title, link, description, date, categories);
            news.site = site;
            result.add(news);

            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        debug("[#] NEWS IN " + site.name + " DATABASE: " + result.size());
        return result;
    }

    /**
     * ******** UPDATES *************
     **/
    public void updateNews(News news) {
        ContentValues values = new ContentValues();
        values.put(DBNews.title, news.title);
        values.put(DBNews.link, news.link);
        values.put(DBNews.date, news.date.getValue());
        values.put(DBNews.description, news.description);
        values.put(DBNews.tags, news.categories.toString());

        SQLiteDatabase database = db.getWritableDatabase();
        database.update(DBNews.db, values, DBNews.id + " = " + news.id, null);
        database.close();
    }

    /**
     * *********************** INSERTS ************************
     **/
    public void insertNews(int sitecode, News news) {
        ContentValues values = new ContentValues();
        values.put(DBNews.site_code, sitecode);
        values.put(DBNews.title, news.title);
        values.put(DBNews.link, news.link);
        values.put(DBNews.date, news.date.getValue());
        values.put(DBNews.description, news.description);
        values.put(DBNews.tags, news.categories.toString());

        SQLiteDatabase database = db.getWritableDatabase();
        news.id = (int) database.insert(DBNews.db, null, values);
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

    private void debug(String text) {
        android.util.Log.d("##DBManager##", text);
    }

}
