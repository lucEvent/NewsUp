package com.lucevent.newsup.io;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.Tags;
import com.lucevent.newsup.kernel.util.HistoryNews;
import com.lucevent.newsup.kernel.util.Note;
import com.lucevent.newsup.services.util.DownloadSchedule;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "newsup.db";
    private static final int DATABASE_VERSION = 3;

    public static final String id = "_id";

    static final class DBNews {
        static final String db = "news";

        static final String site_code = "site_id";
        static final String title = "title";
        static final String link = "link";
        static final String date = "date";
        static final String description = "descr";
        static final String tags = "tags";

        static final String[] cols = {id, site_code, title, link, date, description, tags};

        static final String creator =
                "CREATE TABLE " + db + " (" +
                        id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        site_code + " INTEGER," +
                        title + " TEXT NOT NULL," +
                        link + " TEXT NOT NULL," +
                        date + " INTEGER," +
                        description + " TEXT NOT NULL," +
                        tags + " TEXT NOT NULL" +
                        ");";

        static News parse(Cursor c)
        {
            return new News(c.getInt(0), c.getString(2), c.getString(3), c.getString(5),
                    c.getLong(4), new Tags(c.getString(6)));
        }
    }

    static final class DBiNewsSection {
        static final String db = "isection";

        static final String site_code = "site_id";
        static final String section = "section";
        static final String news_id = "news_id";

        static final String[] cols = {site_code, section, news_id};

        static final String creator =
                "CREATE TABLE " + db + " (" +
                        site_code + " INTEGER," +
                        section + " INTEGER," +
                        news_id + " INTEGER" +
                        ");";

        static int newsId(Cursor c)
        {
            return c.getInt(2);
        }
    }

    static final class DBHistoryNews {
        static final String db = "histnews";

        static final String news_id = "news_id";
        static final String site_code = "site_id";
        static final String title = "title";
        static final String link = "link";
        static final String date = "date";
        static final String description = "descr";
        static final String tags = "tags";

        static final String[] cols = {id, news_id, site_code, title, link, date, description, tags};

        static final String creator =
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

        static HistoryNews parse(Cursor cursor)
        {
            return new HistoryNews(cursor.getInt(0), cursor.getInt(1), cursor.getString(3),
                    cursor.getString(4), cursor.getString(6), cursor.getLong(5),
                    new Tags(cursor.getString(7)), cursor.getInt(2));
        }
    }

    static final class DBDownloadSchedule {
        static final String db = "dl_sched";

        static final String hour = "hour";
        static final String minute = "minute";
        static final String notify = "notify";
        static final String repeat = "repeat";
        static final String day_monday = "monday";
        static final String day_tuesday = "tuesday";
        static final String day_wednesday = "wednesday";
        static final String day_thursday = "thursday";
        static final String day_friday = "friday";
        static final String day_saturday = "saturday";
        static final String day_sunday = "sunday";
        static final String sites_codes = "sites_codes";

        static final String[] cols = {id, hour, minute, notify, repeat, day_monday, day_tuesday,
                day_wednesday, day_thursday, day_friday, day_saturday, day_sunday, sites_codes};

        static final String creator =
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

        static DownloadSchedule parse(Cursor cursor)
        {
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

            return schedule;
        }
    }

    static final class DBReadingStats {
        static final String db = "dl_stats_reading";

        static final String site_code = "site_code";
        static final String readings = "readings";

        static final String[] cols = {site_code, readings};

        static final String creator =
                "CREATE TABLE " + db + " (" +
                        site_code + " INTEGER," +
                        readings + " INTEGER" +
                        ");";

        static Pair<Integer, Integer> parse(Cursor cursor)
        {
            return new Pair<>(cursor.getInt(0), cursor.getInt(1));
        }
    }

    static final class DBNote {
        static final String db = "dl_notes";

        static final String note = "note";

        static final String[] cols = {id, note};

        static final String creator =
                "CREATE TABLE " + db + " (" +
                        id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        note + " TEXT NOT NULL" +
                        ");";

        static Note parse(Cursor cursor)
        {
            return new Note(cursor.getInt(0), cursor.getString(1));
        }
    }

    public Database(Context context)
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
        db.execSQL(DBReadingStats.creator);
        db.execSQL(DBNote.creator);
        db.execSQL(DBiNewsSection.creator);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        AppSettings.printlog("[DB] Upgrading DB from version " + oldVersion + " to " + newVersion);

        switch (oldVersion) {
            case 1:
                db.execSQL(DBReadingStats.creator);
                db.execSQL(DBNote.creator);
            case 2:
                db.execSQL(DBiNewsSection.creator);
            case 3:
                // Delete all needed tables
                // db.execSQL("DROP TABLE "+ "db_xxxxxxxxxxxx");
                // Create all needed tables
                // db.execSQL(CREATE_yyyyyyyyy);
        }

    }

}
