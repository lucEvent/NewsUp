package com.lucevent.newsup.backend.utils;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.data.util.SiteLanguage;
import com.lucevent.newsup.data.util.Tags;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class UpdateMessageCreator {

	private static final String applink = "https://play.google.com/store/apps/details?id=com.lucevent.newsup";

	public static void generateUpdateNews(Site site, HttpServletResponse resp) throws IOException
	{
		StringBuilder title = new StringBuilder();
		StringBuilder description = new StringBuilder();
		String content = generateContent(site);

		switch (site.getLanguage() << SiteLanguage.shift) {
			case SiteLanguage.SPANISH:
				title.append("Actualiza ya a la \u00FAltima versi\u00F3n de NewsUp");
				description.append("Click para m\u00E1s informaci\u00F3n");
				break;

			case SiteLanguage.CATALAN:
				title.append("Actualitza ja a la \u00FAltima versi\u00F3 de NewsUp");
				description.append("Click per a m\u00E9s informaci\u00F3");
				break;

			default:
				title.append("Update to the latest version of NewsUp");
				description.append("Click for more info");
		}

		News news = new News(title.toString(), applink, description.toString(), System.currentTimeMillis(), null, new Tags(), -1, -1, -1);
		news.content = content;

		NewsArray na = new NewsArray();
		na.add(news);
		resp.getWriter().println(BackendParser.toEntry(na).toString());
	}

	public static String generateContent(Site site)
	{
		switch (site.getLanguage() << SiteLanguage.shift) {
			case SiteLanguage.SPANISH:
				return "<p>Es necesario que actualizes la aplicaci\u00F3n para poder seguir utiliz\u00E1ndola.</p>"
						+ "<p><a href='" + applink + "'>Puedes actualizarla directamente desde este enlace</a><p>";

			case SiteLanguage.CATALAN:
				return "<p>Es necessari que actualitzis la aplicaci\u00F3 per poder seguir utilitzant-la.</p>"
						+ "<p><a href='" + applink + "'>Pots actualitzar-la directament des d'aquest enlla\u00E7</a></p>";

			default:
				return "<p>To keep using this app is necessary that you update to the last version.</p>"
						+ "<p><a href='" + applink + "'>You can directly update it through this link</a></p>";
		}
	}

	public static boolean needsUpdate(String version)
	{
		if (version != null && !version.isEmpty()) {

			String[] vs = version.split("\\.");

			return !vs[0].equals("2");

		}
		return true;
	}
}
