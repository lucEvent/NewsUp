package com.lucevent.newsup.io;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.kernel.util.Compressor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SDManager {

	private Context mContext;

	public SDManager(Context context)
	{
		mContext = context;
	}

	public void readNewsContent(News news)
	{
		try {
			FileInputStream inputStream = mContext.openFileInput("n" + news.id);

			news.content = Compressor.decompress(inputStream);

			inputStream.close();
		} catch (FileNotFoundException e) {
			AppSettings.printerror("[SDM] Couldn't find news in storage [news.id: " + news.id + "]", e);
		} catch (Exception e) {
			AppSettings.printerror("[SDM] Error in SDManager.readNews [ news.id: " + news.id + "]", e);
		}
	}

	public void saveNewsContent(News news)
	{
		try {
			FileOutputStream outputStream = mContext.openFileOutput("n" + news.id, Context.MODE_PRIVATE);

			Compressor.compress(news.content, outputStream);

			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			AppSettings.printerror("[SDM] Error in saveNews [news.id = " + news.id + "] ## [title: " + news.title + "]", e);
		}
	}

	public void deleteNews(int news_id)
	{
		try {
			mContext.deleteFile("n" + news_id);
		} catch (Exception e) {
			AppSettings.printerror("[SDM] Error in deleteNews [news.id = " + news_id + "]", e);
		}
	}

	public static File getDirectory()
	{
		File directory;
		if (Environment.getExternalStorageState() == null)
			directory = new File(Environment.getDataDirectory() + "/NewsUp/");
		else
			directory = new File(Environment.getExternalStorageDirectory() + "/NewsUp/");

		if (!directory.exists())
			directory.mkdir();

		return directory;
	}

	public static String readRaw(Context context, int id)
	{
		try {
			BufferedInputStream stream = new BufferedInputStream(context.getResources().openRawResource(id));

			byte[] bytes = new byte[4096];
			int bytesRead;
			StringBuilder sb = new StringBuilder();
			while ((bytesRead = stream.read(bytes, 0, bytes.length)) != -1)
				sb.append(new String(bytes, 0, bytesRead));

			return sb.toString();
		} catch (Exception e) {
			AppSettings.printerror("Error in readRaw", e);
			e.printStackTrace();
		}
		return "";
	}

	public static void downloadFile(Context c, String url)
	{
		String imgFileName = url.substring(url.lastIndexOf("/") + 1, url.length());

		DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

		request.setTitle(imgFileName)
				.setAllowedOverRoaming(false)
				.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
				.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, imgFileName);

		DownloadManager downloadManager = (DownloadManager) c.getSystemService(android.content.Context.DOWNLOAD_SERVICE);
		if (downloadManager != null) {
			downloadManager.enqueue(request);
			Toast.makeText(c, R.string.msg_downloading_image, Toast.LENGTH_SHORT).show();
		}
	}

	public long getSize()
	{
		long size = 0;
		File[] files = mContext.getFilesDir().listFiles();
		for (File f : files) size += f.length();
		return size;
	}

	public long getDBSize()
	{
		return mContext.getDatabasePath(Database.DATABASE_NAME).length();
	}

	public long getGlideCacheSize()
	{
		long size = 0;
		File[] files = Glide.getPhotoCacheDir(mContext).listFiles();
		for (File f : files) size += f.length();
		return size;
	}

	public void wipeData()
	{
		File[] files = mContext.getFilesDir().listFiles();
		for (File f : files) f.delete();
	}

	public void wipeGlideData()
	{
		File glideCache = Glide.getPhotoCacheDir(mContext);
		for (File f : glideCache.listFiles())
			f.delete();
	}

}
