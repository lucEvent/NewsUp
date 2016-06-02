package com.lucevent.newsup.io;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lucevent.newsup.AppSettings;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "newsup.db";
    private static final int DATABASE_VERSION = 1;

    static final class DBNews {
        final static String db = "news";

        final static String id = "_id";
        final static String site_code = "site_id";
        final static String title = "title";
        final static String link = "link";
        final static String date = "date";
        final static String description = "descr";
        final static String tags = "tags";

        final static String[] cols = {id, site_code, title, link, date, description, tags};

        final static String creator =
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

    static final class DBHistoryNews {
        final static String db = "histnews";

        final static String id = "_id";
        final static String news_id = "news_id";
        final static String site_code = "site_id";
        final static String title = "title";
        final static String link = "link";
        final static String date = "date";
        final static String description = "descr";
        final static String tags = "tags";

        final static String[] cols = {id, news_id, site_code, title, link, date, description, tags};

        final static String creator =
                "CREATE TABLE " + db + " (" +
                        id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        news_id + " INTEGER," +
                        site_code + " INTEGER," +
                        title + " TEXT NOT NULL," +
                        link + " TEXT NOT NULL," +
                        date + " INTEGER," +
                        description + " TEXT NOT NULL," +
                        tags + " TEXT NOT NULL" +
                        ");";
    }

    static final class DBDownloadSchedule {
        final static String db = "dl_sched";

        final static String id = "_id";
        final static String hour = "hour";
        final static String minute = "minute";
        final static String notify = "notify";
        final static String repeat = "repeat";
        final static String day_monday = "monday";
        final static String day_tuesday = "tuesday";
        final static String day_wednesday = "wednesday";
        final static String day_thursday = "thursday";
        final static String day_friday = "friday";
        final static String day_saturday = "saturday";
        final static String day_sunday = "sunday";
        final static String sites_codes = "sites_codes";

        final static String[] cols = {id, hour, minute, notify, repeat, day_monday, day_tuesday,
                day_wednesday, day_thursday, day_friday, day_saturday, day_sunday, sites_codes};

        final static String creator =
                "CREATE TABLE " + db + " (" +
                        id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        notify + " INTEGER," +
                        repeat + " INTEGER," +
                        hour + " INTEGER," +
                        minute + " INTEGER," +
                        day_monday + " INTEGER," +
                        day_tuesday + " INTEGER," +
                        day_wednesday + " INTEGER," +
                        day_thursday + " INTEGER," +
                        day_friday + " INTEGER," +
                        day_saturday + " INTEGER," +
                        day_sunday + " INTEGER," +
                        sites_codes + " TEXT NOT NULL" +
                        ");";
    }


    Database(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        AppSettings.printlog("[DB] En onCreate");

        db.execSQL(DBNews.creator);
        db.execSQL(DBHistoryNews.creator);
        db.execSQL(DBDownloadSchedule.creator);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        AppSettings.printlog("[DB] Upgrading DB from version " + oldVersion + " to " + newVersion);

        // Delete all needed tables
        // db.execSQL("DROP TABLE "+ "db_xxxxxxxxxxxx");
        // Create all needed tables
        // db.execSQL(CREATE_yyyyyyyyy);
    }

}
