package com.lucevent.newsup.debugbackend.kernel;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsArray;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.debugbackend.db.Bug;
import com.lucevent.newsup.debugbackend.db.PartialTestResult;
import com.lucevent.newsup.debugbackend.db.Task;
import com.lucevent.newsup.debugbackend.net.Net;
import com.lucevent.newsup.debugbackend.util.ReportCallback;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class Test {

	private long mSiteStartTestTime = -1;
	private int mSiteRoundCounter = 0;

	public Test()
	{
	}

	/**
	 * @return report of the task performed
	 */
	public String doTest(ReportCallback urgentCallback)
	{
		Sites sites = Sites.getDefault(true);
		Evaluator evaluator = new Evaluator();

		Task task = Task.getCurrent(evaluator.size());
		task.newRound();
		mSiteRoundCounter++;

		if (task.getCurrentEvaluatingSite() < sites.size()) {
			Site site = sites.get((int) task.getCurrentEvaluatingSite());

			if (mSiteStartTestTime == -1)
				mSiteStartTestTime = System.currentTimeMillis();

			for (; task.getCurrentEvaluatingSection() < site.getSections().size(); task.currentEvaluatingSectionEnd(), task.save()) {
				Section section = site.getSections().get((int) task.getCurrentEvaluatingSection());
				if (section.url != null) {

					int[] tempValues = new int[task.getSiteTestResults().size()];
					for (int i = 0; i < tempValues.length; i++)
						tempValues[i] = 0;

					NewsArray news = site.readNewsHeaders(new int[]{section.code});
					for (News N : news) {
						if (N.content == null || N.content.isEmpty()) {
//                            try {
//                                Thread.sleep(500);
//                            } catch (InterruptedException ignored) {
//                            }
							site.readNewsContent(N.link);
						}
					}
					int[] partialValues = evaluator.evaluate(news);
					for (int t = 0; t < tempValues.length; t++)
						tempValues[t] += partialValues[t];

					task.addSectionResults(news.size(), tempValues);
				}
			}

			//
			long now = System.currentTimeMillis();
			Net.sendStatus(
					site.code, site.name, site.info,
					now, now - mSiteStartTestTime, mSiteRoundCounter,
					task.getSiteNumNews(), task.getSiteTestResults().get(0)
			);
			mSiteStartTestTime = -1;
			mSiteRoundCounter = 0;

			// Saving results for current Site
			PartialTestResult ptr = new PartialTestResult();
			ptr.setTaskId(task.getId());
			ptr.setSiteCode(site.code);
			ptr.setNumNews(task.getSiteNumNews());
			ptr.setTestResults(task.getSiteTestResults());
			ptr.save();

			if (task.getSiteNumNews() == 0 || (task.getSiteNumNews() / 2) < task.getSiteTestResults().get(0))
				urgentCallback.report(site.name + " | " + task.getSiteNumNews() + " news read / " + task.getSiteTestResults().get(0) + " without content");

			task.addSiteResults(task.getSiteNumNews(), task.getSiteTestResults());
			task.currentEvaluatingSiteEnd();
			task.setCurrentEvaluatingSection(0);
			task.setSiteNumNews(0);
			task.save();

			return null;
		}
		task.end();

		TreeSet<PartialTestResult> partials = new TreeSet<>(new Comparator<PartialTestResult>() {
			@Override
			public int compare(PartialTestResult o1, PartialTestResult o2)
			{
				return Long.compare(o1.getSiteCode(), o2.getSiteCode());
			}
		});
		partials.addAll(PartialTestResult.getAll(task.getId()));

		// Creating report
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < evaluator.size(); ++i) {
			sb.append("** ").append(evaluator.getDescription(i)).append(" **\n");
			for (PartialTestResult ptr : partials) {
				ArrayList<Long> testResults = ptr.getTestResults();
				if (i < testResults.size() && testResults.get(i) > 0)
					sb.append("\t *").append(sites.getSiteByCode((int) ptr.getSiteCode()).name).append(" (").append(testResults.get(i)).append(")\n");
			}
		}
		sb.append("\n** Resultados totales **\n");
		sb.append("\t").append(task.getTotalNumNews()).append(" noticias\n");
		ArrayList<Long> totalTestResults = task.getTotalTestResults();
		for (int i = 0; i < totalTestResults.size(); i++) {
			long res = totalTestResults.get(i);
			if (res > 0)
				sb.append("\t").append(res).append(" ").append(evaluator.getDescription(i)).append("\n");
		}

		return sb.toString();
	}

	public void clearTestCache()
	{
		PartialTestResult.deleteAll();
	}

	private class Evaluator {

		private static final int D_EMPTY = 0;
		private static final int D_HS = 1;
		private static final int D_SCRIPTS = 2;
		private static final int D_LINKS = 3;
		private static final int D_STYLES_TAGS = 4;
		private static final int D_STYLE_ATTR = 5;
		private static final int D_A_OBJECT = 6;
		private static final int D_COMMENTS = 7;
		private static final int D_EMPTY_SECTION = 8;

		private final String[] descriptions = new String[]{
				"estan vac\u00EDas",     //
				"contienen h1/h2",       //
				"tienen scripts",        //
				"con links sin HTTP",    //
				"tienen style tags",     //
				"tienen style attrs",    //
				"tienen <a><object></a>",//
				"tienen comentarios",    //
				"secciones vacias"
		};

		public int[] evaluate(NewsArray news)
		{
			int[] res = new int[descriptions.length];
			for (int i = 0; i < res.length; i++)
				res[i] = 0;

			for (News n : news) {
				Document doc = org.jsoup.Jsoup.parse(n.content);

				if (evaluateEmpty(n.content)) {
					res[D_EMPTY]++;
				}
				if (evaluateHs(doc)) {
					new Bug(n, "h1 or h2").save();
					res[D_HS]++;
				}
				if (evaluateScripts(doc)) {
					res[D_SCRIPTS]++;
				}
				if (evaluateLinks(n.content)) {
					String description = "descr: ";
					int i0 = n.content.indexOf("=\"//");
					if (i0 == -1) i0 = n.content.indexOf("='//");
					i0 = Math.max(0, i0 - 20);
					int i1 = Math.min(i0 + 40, n.content.length() - 1);
					description += n.content.substring(i0, i1);

					new Bug(n, description).save();
					res[D_LINKS]++;
				}
				if (evaluateStyleTag(doc)) {
					new Bug(n, "style tag").save();
					res[D_STYLES_TAGS]++;
				}
				if (evaluateStyleAttr(doc)) {
					res[D_STYLE_ATTR]++;
				}
				if (evaluateAWithObject(doc)) {
					new Bug(n, "a tag with object").save();
					res[D_A_OBJECT]++;
				}
				if (evaluateComments(n.content)) {
					new Bug(n, "comments").save();
					res[D_COMMENTS]++;
				}
			}

			if (news.isEmpty())
				res[D_EMPTY_SECTION]++;

			return res;
		}

		private boolean evaluateHs(Document doc)
		{
			return !doc.select("h1,h2").isEmpty();
		}

		private boolean evaluateEmpty(String content)
		{
			return content == null || content.isEmpty();
		}

		private boolean evaluateScripts(Document doc)
		{
			return !doc.select("script").isEmpty();
		}

		private boolean evaluateLinks(String content)
		{
			return content.contains("=\"//") || content.contains("='//");
		}

		private boolean evaluateStyleTag(Document doc)
		{
			return !doc.select("style").isEmpty();
		}

		private boolean evaluateStyleAttr(Document doc)
		{
			return !doc.select("[style]").isEmpty();
		}

		private boolean evaluateAWithObject(Document doc)
		{
			return !doc.select("a:has(img,iframe,video,figure,picture)").isEmpty();
		}

		private boolean evaluateComments(String content)
		{
			return content.contains("<!--");
		}

		int size()
		{
			return descriptions.length;
		}

		String getDescription(int position)
		{
			return descriptions[position];
		}

	}

}
