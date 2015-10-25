package com.newsup.io;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "newsup.db";
    private static final int DATABASE_VERSION = 1;

    static final class DBNews {
        static String db = "news";

        static String id = "_id";
        static String site_code = "site_id";
        static String title = "title";
        static String link = "link";
        static String date = "date";
        static String description = "descr";
        static String tags = "tags";

        static String[] cols = {id, site_code, title, link, date, description, tags};

        static String creator =
                "CREATE TABLE " + db + " (" +
                        id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        site_code + " INTEGER," +
                        title + " TEXT NOT NULL," +
                        link + " TEXT NOT NULL," +
                        date + " INTEGER," +
                        description + " TEXT NOT NULL," +
                        tags + " TEXT NOT NULL" +
                        ");";
    }

    Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        debug("En onCreate");

        db.execSQL(DBNews.creator);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        debug("Upgrading DB from VERS: " + oldVersion + " to " + newVersion);

        // Delete all needed tables
        // db.execSQL("DROP TABLE "+ "db_tiempo_verbal");
        // Create all needed tables
        // db.execSQL(CREATE_THEME);
    }

    private void debug(String text) {
        android.util.Log.d("##DataBase##", text);
    }

}
