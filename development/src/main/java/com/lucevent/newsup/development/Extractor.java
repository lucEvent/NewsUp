package com.lucevent.newsup.development;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.development.utils.Pair;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static com.lucevent.newsup.data.util.Reader.USER_AGENT;

public class Extractor {

	public enum FeedAt {
		START, MIDDLE, END
	}

	class SiteData {

		ArrayList<Pair<String, String>> metas;
		Sections sections;
	}

	public static void main(String[] args) throws IOException
	{
		Extractor e = new Extractor();

		//  am.processPetition("TheJapanTimes", "https://www.japantimes.co.jp/", "feed", FeedAt.END);
		//     am.processPetition("EUObserver", "https://euobserver.com/", "feed", FeedAt.END);

		e.processPetition("motherboard", "https://motherboard.vice.com/es", "/rss", FeedAt.END);
	}

	private SiteData processPetition(String name, String url, String feedTag, FeedAt feedAt) throws IOException
	{
		boolean printMeta = true;
		boolean printSections = true;
		boolean printDebugLine = true;

		Document d = fetchUrl(url);
		if (d == null) {
			System.out.println("Null content");
			return null;
		}

		SiteData data = new SiteData();
		data.metas = getMeta(d, printMeta);
		data.sections = getSections(url, d, feedTag, feedAt, printSections);
		createFiles(name, data);

		return data;
	}

	private String convert(String url, String feedTag, FeedAt feedAt)
	{
		switch (feedAt) {
			case START:
				//todo
				break;
			case MIDDLE:
				//todo
				break;
			case END:
				if (url.endsWith("/")) {
					url = url + feedTag;
				} else {
					url = url + "/" + feedTag;
				}
				break;
		}
		return url;
	}

	private ArrayList<Pair<String, String>> getMeta(Document d, boolean printAll)
	{
		Elements meta = d.select("meta");
		if (printAll) {
			System.out.println("# # METAS # #");
			for (Element m : meta) {
				for (Attribute a : m.attributes()) {
					System.out.print("[" + a.getKey() + "=" + a.getValue() + "] ");
				}
				System.out.println();
			}
		}
		ArrayList<Pair<String, String>> metas = new ArrayList<>();
		addMeta(metas, meta, "[charset]", "charset", "charset");
		addMeta(metas, meta, "[name=thumbnail]", "icon", "content");
		addMeta(metas, meta, "[name=description]", "dscr", "content");
		addMeta(metas, meta, "[property=og:locale]", "locale", "content");
		addMeta(metas, meta, "[property=og:site_name]", "name", "content");
		addMeta(metas, meta, "[property=og:title]", "name", "content");
		addMeta(metas, meta, "[property=og:image]", "icon", "content");
		addMeta(metas, meta, "[name=twitter:image]", "icon", "content");
		//     addMeta(metas,meta,"[]","","");
		return metas;
	}

	private void addMeta(ArrayList<Pair<String, String>> a, Elements e, String select, String name, String attr)
	{
		Elements t = e.select(select);
		if (!t.isEmpty()) {
			a.add(new Pair<>(name, t.get(0).attr(attr)));
		}
	}

	private Sections getSections(String url, Document d, String feedTag, FeedAt feedAt, boolean printAll)
	{
		Sections sections = new Sections();

		Elements navs = d.select("nav,.menu");
		for (Element nav : navs) {
			System.out.println("# # # # # #");
			System.out.println("# Attributes #");
			for (Attribute a : nav.attributes()) {
				System.out.println(a.getKey() + "=" + a.getValue());
			}
			System.out.println("# Links #");
			for (Element l : nav.select("a")) {
				String sectionName = l.text();
				String sectionLink = l.attr("href");
				System.out.println(l.text() + "=" + l.attr("href"));

				if (sectionLink.startsWith("//")) {
					sectionLink = "http:" + sectionLink;
				} else if (sectionLink.startsWith("/")) {
					sectionLink = url + sectionLink.substring(1);
				}

				sections.add(new Section(
						sectionName,
						convert(sectionLink, feedTag, feedAt),
						0));
			}
			System.out.println("");
		}

		System.out.println("\n#############");
		System.out.println("# Sections #");
		for (int i = 0; i < sections.size(); ) {
			Section s = sections.remove(i);

			Document sectionD = fetchUrl(s.url);
			if (sectionD != null) {
				System.out.println(s.name + "->" + sectionD.select("item,entry").size());
			}
			if (sectionD == null || sectionD.select("item,entry").isEmpty()) {
				continue;
			}
			sections.add(i, new Section(s.name, sectionD.baseUri(), s.level));
			i++;
		}
		return sections;
	}

	private void createFiles(String site_name, SiteData data)
	{
		for (Section s : data.sections) {
			System.out.println("add(new Section(\"" + s.name + "\", \"" + s.url + "\", 0));");
		}
		System.out.println();
		System.out.println("s = new Site(0, \"" + site_name + "\", 0, \"\", 0, " + site_name + "Sections.class, " + site_name + ".class);");
	}

	private Document fetchUrl(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.userAgent(USER_AGENT)
					.timeout(10000)
					//         .validateTLSCertificates(false)
					.get();
		} catch (Exception e) {
			System.out.println("[" + e.getClass().getSimpleName() + "] Can't read page. Trying again");
		}
		return null;
	}

}
