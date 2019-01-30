package com.lucevent.newsup.development.utils;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;

import org.jsoup.nodes.Document;

public class FullTest {

	public static final Test TH2 = new Test() {
		@Override
		public String testDescription()
		{
			return "contienen h1/h2";
		}

		@Override
		public boolean evaluate(Site site, News news)
		{
			boolean b = news.content.contains("<h2") || news.content.contains("<h1");
			if (b) {
				System.out.println("H2 o H1");
				HardDrive.copy(news, "h2" + news.title.hashCode());
			}
			return b;
		}
	};
	public static final Test WITHCONTENT = new Test() {
		@Override
		public String testDescription()
		{
			return "est\u00e1n vac\u00edas";
		}

		private boolean show = false;

		@Override
		public boolean evaluate(Site site, News news)
		{
			boolean b = news.content == null || news.content.isEmpty();
			if (b && show) {
				System.out.println("WITHOUT CONTENT: " + news.link);
				//HardDrive.copy(news, "woc" + news.title.hashCode());
			}
			return b;
		}
	};
	public static Test SCRIPTS = new Test() {
		@Override
		public String testDescription()
		{
			return "tienen scripts";
		}

		private boolean show = !false;

		@Override
		public boolean evaluate(Site site, News news)
		{
			boolean b = news.content.contains("<script");
			if (b && show) {
				System.out.println("t;" + news.title);
				String script = Utils.findSubstringBetween(news.content, "<script", "</script>", true);
				//     if (!script.contains("twitter") && !script.contains("instagram")) {
				System.out.println("->" + script);
				HardDrive.copy(news, "script" + news.title.hashCode());
				//      }
			}
			return b;
		}
	};
	public static final Test LINKS = new Test() {
		@Override
		public String testDescription()
		{
			return "con links partidos";
		}

		private boolean show = !false;

		@Override
		public boolean evaluate(Site site, News news)
		{
			boolean b = news.content.contains("=\"//") || news.content.contains("='//");

			if (b && show) {
				System.out.println("\nNeeds repair links [" + news.title + "]");
				int i = news.content.indexOf("=\"//");
				i = i != -1 ? i : news.content.indexOf("='//");

				System.out.println("LINK:" + news.content.substring(Math.max(0, i - 20), i + 30));
				HardDrive.copy(news, "link__" + news.title.hashCode());
				return b;
			}
			return b;
		}
	};
	public static final Test STYLES = new Test() {
		@Override
		public String testDescription()
		{
			return "tienen styles";
		}

		private boolean show = false;

		@Override
		public boolean evaluate(Site site, News news)
		{
			boolean b1 = news.content.contains("</style>");

			if (b1 && show) {
				System.out.println("\nHas tag <style></styles>: [" + news.title + "]");

				Document doc = org.jsoup.Jsoup.parse(news.content);
				for (org.jsoup.nodes.Element e : doc.select("style")) {
					System.out.println(" * " + e.outerHtml());
				}
				//   HardDrive.copy(news, "style" + news.title.hashCode());
			}
			boolean b2 = news.content.contains("style=");
			if (b2 && show) {
				System.out.println("\nHas param styles: [" + news.title + "]");

				Document doc = org.jsoup.Jsoup.parse(news.content);
				for (org.jsoup.nodes.Element e : doc.select("[style]:not(.instagram-media,.instagram-media *)")) {
					System.out.println(" * " + e.tagName() + ": " + e.attr("style"));
				}
				//     HardDrive.copy(news, "style" + news.title.replace(" ", "_").substring(0, Math.min(10, news.title.length())));
			}
			return b1 || b2;
		}
	};
	public static final Test COMMENTS = new Test() {
		@Override
		public String testDescription()
		{
			return "tienen comentarios";
		}

		private boolean show = false;

		@Override
		public boolean evaluate(Site site, News news)
		{
			boolean b = news.content.contains("<!--");
			if (b && show) {
				System.out.println("");
				int endi = 0;
				for (int i = news.content.indexOf("<!--", endi); i != -1; i = news.content.indexOf("<!--", endi)) {
					endi = news.content.indexOf("-->", i);
					System.out.println(">" + news.content.substring(i, endi));
				}
				HardDrive.copy(news, "comment" + news.title.hashCode());
			}
			return b;
		}
	};

	public static final Test A_WITH_OBJECT = new Test() {
		@Override
		public String testDescription()
		{
			return "tienen <a><object></a>";
		}

		@Override
		public boolean evaluate(Site site, News news)
		{
			boolean b = !org.jsoup.Jsoup.parse(news.content).select("a:has(img,iframe,video,figure,picture)").isEmpty();

			if (b) {
				HardDrive.copy(news, "comment" + news.title.hashCode());
			}

			return b;
		}
	};

	public static final int testSite(Site site, Test[] tests, int[] testResults)
	{
		//print tests that will be performed
		for (Test t : tests)
			System.out.print(t.testDescription() + " | ");
		System.out.println();
		//
		int numNews = 0;
		Sections sections = site.getSections();
		for (int i = 0; i < sections.size(); i++) {
			Section sc = sections.get(i);
			if (sc.url != null) {
				System.out.println(".." + (i + 1) + "/" + sections.size() + "..");

				NewsArray news = site.readNewsHeaders(new int[]{sc.code});
				numNews += news.size();
				for (News N : news) {
					if (N.content == null || N.content.isEmpty()) {
//                            try {
//                                Thread.sleep(500);
//                            } catch (InterruptedException ex) {
//                                System.out.println("InterruptedException: " + ex.toString());
//                            }
						String content = site.readNewsContent(N.link);
						if (content != null)
							N.content = content;
					}
					for (int t = 0; t < tests.length; t++) {
						Test test = tests[t];
						if (test.evaluate(site, N)) {
							testResults[t]++;
						}
					}
				}
				// print section results
				for (int k : testResults)
					System.out.print(k + " | ");
				System.out.println();
				//
			}
		}
		return numNews;
	}

	public static final int testSiteInServer(Site site, Test[] tests, int[] testResults)
	{
		int numNews = 0;
		ServerReader server = new ServerReader();
		Sections sections = site.getSections();
		for (int i = 0; i < sections.size(); i++) {
			Section sc = sections.get(i);
			if (sc.url != null) {
				System.out.println(".." + (i + 1) + "/" + sections.size() + "..");

				NewsArray news = server.readNewsHeaders(site.code, new int[]{sc.code});
				numNews += news.size();
				for (News N : news) {
					if (N.content == null || N.content.isEmpty()) {
//                            try {
//                                Thread.sleep(500);
//                            } catch (InterruptedException ex) {
//                                System.out.println("InterruptedException: " + ex.toString());
//                            }
						server.readNewsContent(4, N);
					}
					for (int t = 0; t < tests.length; t++) {
						Test test = tests[t];
						if (test.evaluate(site, N)) {
							testResults[t]++;
						}
					}
				}
			}
		}
		return numNews;
	}

}
