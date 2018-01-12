package com.lucevent.newsup.io;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.Tags;
import com.lucevent.newsup.kernel.util.Note;
import com.lucevent.newsup.services.util.Download;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "newsup.db";
    private static final int DATABASE_VERSION = 4;

    public static final String id = "_id";

    static final class DBNews {
        static final String db = "t_news";

        static final String site_code = "site_id";
        static final String title = "title";
        static final String link = "link";
        static final String date = "date";
        static final String description = "descr";
        static final String tags = "tags";
        static final String section_code = "sect_id";
        static final String read_on = "read_on";

        static final String[] cols = {id, title, link, description, date, tags, site_code, section_code, read_on};

        static final String creator =
                "CREATE TABLE " + db + " (" +
                        id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        title + " TEXT NOT NULL," +
                        link + " TEXT NOT NULL," +
                        description + " TEXT NOT NULL," +
                        date + " INTEGER," +
                        tags + " TEXT NOT NULL," +
                        site_code + " INTEGER," +
                        section_code + " INTEGER," +
                        read_on + " INTEGER" +
                        ");";

        static News parse(Cursor c)
        {
            return new News(
                    c.getInt(0),    // id
                    c.getString(1), // title
                    c.getString(2), // link
                    c.getString(3), // description
                    c.getLong(4),   // data
                    new Tags(c.getString(5)),    // tags
                    c.getInt(6),    // site_code
                    c.getInt(7),    // section_code
                    c.getLong(8)     // readOn
            );
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
                        id + " INTEGER PRIMARY KEY," +
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

        static Download parse(Cursor cursor)
        {
            Download schedule = new Download(cursor.getInt(0));
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

    static class DBReadings {
        static final String db = "t_readings";

        static final String site_code = "site_code";
        static final String readings = "readings";
        static final String readings_since_last_sync = "readings_sls";

        static final String[] cols = {site_code, readings, readings_since_last_sync};

        static final String creator =
                "CREATE TABLE " + db + " (" +
                        site_code + " INTEGER," +
                        readings + " INTEGER," +
                        readings_since_last_sync + " INTEGER" +
                        ");";

        static Pair<Integer, Integer> parseAll(Cursor cursor)
        {
            return new Pair<>(cursor.getInt(0), cursor.getInt(1));
        }

        public static Pair<Integer, Integer> parseSync(Cursor cursor)
        {
            return new Pair<>(cursor.getInt(0), cursor.getInt(2));
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
        db.execSQL(DBReadings.creator);
        db.execSQL(DBDownloadSchedule.creator);
        db.execSQL(DBNote.creator);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        AppSettings.printlog("[DB] Upgrading DB from version " + oldVersion + " to " + newVersion);

        switch (oldVersion) {
            case 1:
                db.execSQL("CREATE TABLE dl_stats_reading (v1 INTEGER,v2 INTEGER)");
                db.execSQL(DBNote.creator);
            case 2:
                db.execSQL("CREATE TABLE isection (v1 INTEGER)");
            case 3:
                db.delete("isection", null, null);
                db.delete("histnews", null, null);
                db.delete("news", null, null);

                db.execSQL("DROP TABLE isection");
                db.execSQL("DROP TABLE histnews");
                db.execSQL("DROP TABLE news");

                db.execSQL(DBNews.creator);
                db.execSQL(DBReadings.creator);

                Cursor cursor = db.query("dl_stats_reading", new String[]{"site_code", "readings"}, null, null, null, null, null);
                if (cursor.moveToFirst())
                    do {
                        int v1 = cursor.getInt(0);
                        int v2 = cursor.getInt(1);

                        ContentValues values = new ContentValues();
                        values.put(DBReadings.site_code, v1);
                        values.put(DBReadings.readings, v2);
                        values.put(DBReadings.readings_since_last_sync, v2);
                        db.insert(DBReadings.db, null, values);
                    } while (cursor.moveToNext());

                cursor.close();

                db.delete("dl_stats_reading", null, null);
                db.execSQL("DROP TABLE dl_stats_reading");

                //  case 4:
                // Delete all needed tables
                // db.execSQL("DROP TABLE "+ "db_xxxxxxxxxxxx");
                // Create all needed tables
                // db.execSQL(CREATE_yyyyyyyyy);
        }
        AppSettings.printlog("[DB] Upgrading done");
    }

}
