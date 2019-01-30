package com.lucevent.newsup.io;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.NewsMap;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.UserSite;
import com.lucevent.newsup.io.Database.DBDownloadData;
import com.lucevent.newsup.io.Database.DBDownloadSchedule;
import com.lucevent.newsup.io.Database.DBNews;
import com.lucevent.newsup.io.Database.DBNote;
import com.lucevent.newsup.io.Database.DBReadings;
import com.lucevent.newsup.io.Database.DBUserSite;
import com.lucevent.newsup.kernel.util.Note;
import com.lucevent.newsup.kernel.util.Notes;
import com.lucevent.newsup.services.util.Download;
import com.lucevent.newsup.services.util.DownloadData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DBManager {

	private Database db;

	public DBManager(Context context)
	{
		db = new Database(context);
/*
        synchronized (this) {
            SQLiteDatabase database = db.getWritableDatabase();
            database.delete(DBUserSite.db, null, null);
            database.close();
        }*/
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

	public NewsArray readNews(int[] ids)
	{
		NewsArray news = new NewsArray(ids.length);

		synchronized (this) {
			SQLiteDatabase database = db.getReadableDatabase();

			for (int id : ids) {
				Cursor cursor = database.query(DBNews.db, DBNews.cols, Database.id + "=" + id, null, null, null, null);

				if (cursor.moveToFirst())
					news.add(DBNews.parse(cursor));

				cursor.close();
			}

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

	public Download readDownload(int id)
	{
		Download result = null;

		synchronized (this) {
			SQLiteDatabase database = db.getReadableDatabase();
			Cursor cursor = database.query(DBDownloadSchedule.db, DBDownloadSchedule.cols, Database.id + "=" + id, null, null, null, null);

			if (cursor.moveToFirst())
				result = DBDownloadSchedule.parse(cursor);

			cursor.close();
			database.close();
		}
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
					Site s = sites.getSiteByCode(data.first);
					if (s != null)    // needed in case a Site is removed
						s.setNumReadings(data.second);

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

	public ArrayList<UserSite> readUserSites()
	{
		ArrayList<UserSite> result = new ArrayList<>();

		synchronized (this) {
			SQLiteDatabase database = db.getReadableDatabase();
			Cursor cursor = database.query(DBUserSite.db, DBUserSite.cols, null, null, null, null, null);

			if (cursor.moveToFirst())
				do {

					result.add(DBUserSite.parse(cursor));

				} while (cursor.moveToNext());

			cursor.close();
			database.close();
		}
		return result;
	}

	public DownloadData readNotification(long time)
	{
		DownloadData result = null;
		synchronized (this) {
			SQLiteDatabase database = db.getReadableDatabase();
			Cursor cursor = database.query(DBDownloadData.db, DBDownloadData.cols, DBDownloadData.time + "=" + time, null, null, null, null);

			if (cursor.moveToFirst())
				result = DBDownloadData.parse(cursor);

			cursor.close();
			database.close();
		}
		return result;
	}

	public ArrayList<DownloadData> readNotifications()
	{
		ArrayList<DownloadData> result = new ArrayList<>();

		synchronized (this) {
			SQLiteDatabase database = db.getReadableDatabase();
			Cursor cursor = database.query(DBDownloadData.db, DBDownloadData.cols, null, null, null, null, DBDownloadData.time + " DESC");

			if (cursor.moveToFirst())
				do {

					result.add(DBDownloadData.parse(cursor));

				} while (cursor.moveToNext());

			cursor.close();
			database.close();
		}
		return result;
	}

	public boolean hasNews(int news_id)
	{
		boolean r;
		synchronized (this) {
			SQLiteDatabase database = db.getReadableDatabase();
			Cursor cursor = database.query(DBNews.db, DBNews.cols, Database.id + "=" + news_id, null, null, null, null);

			r = cursor.moveToFirst();

			cursor.close();
			database.close();
		}
		return r;
	}

	/**
	 * ******** UPDATES *************
	 **/
	public void updateUserSite(UserSite site)
	{
		ContentValues values = new ContentValues();
		values.put(DBUserSite.name, site.name);
		values.put(DBUserSite.color, site.color);
		values.put(DBUserSite.url, site.url);
		values.put(DBUserSite.info, site.info);
		values.put(DBUserSite.rssUrl, site.rssUrl);
		values.put(DBUserSite.iconUrl, site.icon);

		synchronized (this) {
			SQLiteDatabase database = db.getWritableDatabase();
			database.update(DBUserSite.db, values, DBUserSite.code + " = " + site.code, null);
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
	private void fill(ContentValues values, News news)
	{
		values.put(Database.id, news.id);
		values.put(DBNews.site_code, news.site_code);
		values.put(DBNews.title, news.title);
		values.put(DBNews.link, news.link);
		values.put(DBNews.date, news.date);
		values.put(DBNews.description, news.description);
		values.put(DBNews.image, news.imgSrc);
		values.put(DBNews.tags, news.tags.toString());
		values.put(DBNews.section_code, news.section_code);
		values.put(DBNews.read_on, 0);
	}

	public void insertNews(News news)
	{
		ContentValues values = new ContentValues();
		fill(values, news);

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
			if (database.update(DBNews.db, values, Database.id + " = " + news.id, null)
					== 0) {
				fill(values, news);
				values.put(DBNews.read_on, news.readOn);
				database.insert(DBNews.db, null, values);
			}
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

	public void insert(DownloadData data)
	{
		ContentValues values = new ContentValues();
		values.put(DBDownloadData.time, data.time);

		String aux = Arrays.toString(data.site_codes);
		values.put(DBDownloadData.sites_codes, aux.substring(1, aux.length() - 1));
		aux = Arrays.toString(data.news_ids);
		values.put(DBDownloadData.news_ids, aux.substring(1, aux.length() - 1));

		synchronized (this) {
			SQLiteDatabase database = db.getWritableDatabase();
			database.insert(DBDownloadData.db, null, values);
			database.close();
		}
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

	public boolean insertUserSite(UserSite site)
	{
		ContentValues values = new ContentValues();
		values.put(DBUserSite.code, site.code);
		values.put(DBUserSite.name, site.name);
		values.put(DBUserSite.color, site.color);
		values.put(DBUserSite.url, site.url);
		values.put(DBUserSite.info, site.info);
		values.put(DBUserSite.rssUrl, site.rssUrl);
		values.put(DBUserSite.iconUrl, site.icon);

		synchronized (this) {
			SQLiteDatabase database = db.getWritableDatabase();
			database.insert(DBUserSite.db, null, values);
			database.close();
		}
		return true;
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

	public void delete(DownloadData data)
	{
		synchronized (this) {
			SQLiteDatabase database = db.getWritableDatabase();
			database.delete(DBDownloadData.db, DBDownloadData.time + "=" + data.time, null);
			database.close();
		}
	}

	public void clearDownloadData()
	{
		synchronized (this) {
			SQLiteDatabase database = db.getWritableDatabase();
			database.delete(DBDownloadData.db, null, null);
			database.close();
		}
	}

	public void wipeData()
	{
		synchronized (this) {
			SQLiteDatabase database = db.getWritableDatabase();
			database.delete(DBNews.db, null, null);
			database.delete(DBDownloadData.db, null, null);
			database.close();
		}
	}

	public void deleteDataOf(int[] site_codes)
	{
		synchronized (this) {
			SQLiteDatabase database = db.getWritableDatabase();
			for (int code : site_codes) {
				database.delete(DBNews.db, DBNews.site_code + "=" + code, null);
				database.delete(DBReadings.db, DBReadings.site_code + "=" + code, null);
			}
			database.close();
		}
	}

}
