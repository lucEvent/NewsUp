package com.lucevent.newsup.view.util;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.util.News;

public class Utils {

	public static int brighter(int color, double factor)
	{
		int r = (color >> 16) & 0xFF;
		int g = (color >> 8) & 0xFF;
		int b = color & 0xFF;

		int i = (int) (1.0 / (1.0 - factor));
		if (r == 0 && g == 0 && b == 0) {
			return Color.rgb(i, i, i);
		}
		if (r > 0 && r < i) r = i;
		if (g > 0 && g < i) g = i;
		if (b > 0 && b < i) b = i;

		return Color.rgb(Math.min((int) (r / factor), 255),
				Math.min((int) (g / factor), 255),
				Math.min((int) (b / factor), 255));
	}

	public static int darker(int color, double factor)
	{
		int r = (color >> 16) & 0xFF;
		int g = (color >> 8) & 0xFF;
		int b = color & 0xFF;
		return Color.rgb(Math.max((int) (r * factor), 0),
				Math.max((int) (g * factor), 0),
				Math.max((int) (b * factor), 0));
	}

	public static void openCustomTab(Context c, String url)
	{
		CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
		builder.setToolbarColor(c.getResources().getColor(R.color.colorPrimary));
		builder.setShowTitle(true);

		CustomTabsIntent customTabsIntent = builder.build();
		customTabsIntent.launchUrl(c, Uri.parse(url));
	}

	public static void openCustomTab(Context c, News n)
	{
		openCustomTab(c, n.link);
	}

}
