package com.lucevent.newsup.io;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.data.util.UserSite;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class LogoManager {

	public enum Size {
		DRAWER, I_ITEM, ACTION_BAR, SELECT_SCREEN
	}

	private static Context mContext;
	private static AssetManager dataManager;

	private static SparseArray<Drawable> drawer_logos, i_item_logos, bar_logos, select_screen_logos;

	public static LogoManager getInstance(Context context, int size)
	{
		mContext = context;
		dataManager = context.getAssets();

		drawer_logos = new SparseArray<>(size);
		i_item_logos = new SparseArray<>(size);
		bar_logos = new SparseArray<>(size);
		select_screen_logos = new SparseArray<>(size);

		return new LogoManager();
	}

	public static Drawable getLogo(int site_code, LogoManager.Size size)
	{
		SparseArray<Drawable> map;

		switch (size) {
			case DRAWER:
				map = drawer_logos;
				break;
			case I_ITEM:
				map = i_item_logos;
				break;
			case ACTION_BAR:
				map = bar_logos;
				break;
			case SELECT_SCREEN:
				map = select_screen_logos;
				break;
			default:
				return null;
		}

		Drawable res = map.get(site_code);
		if (res == null) {
			try {
				InputStream inputStream = openLogo(site_code);
				res = Drawable.createFromResourceStream(null, null, inputStream, null, null);
				inputStream.close();

				map.append(site_code, res);
			} catch (Exception e) {
				AppSettings.printerror("[LM] Couldn't read asset " + site_code + ".png", e);
			}
		}
		return res;
	}

	public static Bitmap homeScreenIcon(Context context, int site_code)
	{
		return iconBitmap(context, site_code, 1.25f);
	}

	private static Bitmap iconBitmap(Context context, int site_code, float resizeValue)
	{
		Bitmap bitmap = null;
		try {
			InputStream inputStream = openLogo(site_code);
			bitmap = BitmapFactory.decodeStream(inputStream);
			inputStream.close();

			int min = Math.min(Math.round(((float) ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getLauncherLargeIconSize()) * resizeValue), Math.max(bitmap.getWidth(), bitmap.getHeight()));
			int round = Math.round(0.105454547f * ((float) min));
			int i = min + (round * 2);

			Bitmap finalBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(finalBitmap);
			Paint paint = new Paint(1);
			paint.setFilterBitmap(true);
			Rect rect = new Rect(round, round, i - round, i - round);
			canvas.drawBitmap(bitmap, null, rect, paint);
			return finalBitmap;

		} catch (Exception e) {
			AppSettings.printerror("[LM] [EXCEPTION] Couldn't read asset " + site_code + ".png", e);
		}
		return bitmap;
	}

	private static InputStream openLogo(int site_code) throws IOException
	{
		return site_code < 10000 ?
				dataManager.open(site_code + ".png") :
				mContext.openFileInput("l" + site_code);
	}

	public void downloadLogo(final UserSite site)
	{
		new Thread(new Runnable() {
			@Override
			public void run()
			{
				try {
					BufferedInputStream in = new BufferedInputStream(new URL(site.icon).openStream());
					FileOutputStream out = mContext.openFileOutput("l" + site.code, Context.MODE_PRIVATE);

					byte[] buffer = new byte[1024];
					int len = buffer.length;
					while ((len = in.read(buffer, 0, len)) > 0)
						out.write(buffer, 0, len);

					in.close();
					out.flush();
					out.close();
				} catch (Exception e) {
					AppSettings.printerror("[LM] [EXCEPTION] Couldn't download image | " + site.icon, e);
				}
			}
		}).start();
	}

}
