package com.lucevent.newsup.development.utils;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.NewsSet;
import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;

import java.util.ArrayList;
import java.util.TreeSet;

public class SectionsTest {

	private static final int NUM_TESTS = 4;
	private static final int AGE_THRESHOLD_MONTHS = 5;
	private static final long AGE_THRESHOLD = (AGE_THRESHOLD_MONTHS + 1) * 30 * 24 * 60 * 60 * 1000L;
	private static final long MIN_NEWS_PER_SECTION = 1;

	public SectionsTest()
	{
	}

	public void doTest(Site site)
	{
		Sections sections = site.getSections();
		ArrayList<String> secNames = new ArrayList<>(site.getSections().size());
		ArrayList<NewsSet> news = new ArrayList<>(site.getSections().size());
		for (int i = 0; i < sections.size(); i++) {
			Section section = sections.get(i);
			System.out.print(".");
			if (section.url != null) {
				secNames.add(section.name);
				news.add(new NewsSet(site.readNewsHeaders(new int[]{section.code})));
			}
		}
		System.out.println("\n\n ### Results ###");

		int emptySectionsCounter = 0;
		int oldSectionsCounter = 0;
		int repeatedNameSectionsCounter = 0;
		int repeatedUrlSectionsCounter = 0;
		int repeatedSectionsContentCounter = 0;

		// Test 1: Empty sections
		// Test 2: Old Sections
		final long oldestAge = System.currentTimeMillis() - AGE_THRESHOLD;
		for (NewsSet ns : news) {
			// Test 1
			if (ns.size() < MIN_NEWS_PER_SECTION) {
				emptySectionsCounter++;
			}
			// Test 2
			if (ns.size() > 0 && ns.first().date < oldestAge) {
				oldSectionsCounter++;
			}
		}
		System.out.println("Empty sections: " + emptySectionsCounter);
		System.out.println("Old sections: " + oldSectionsCounter);

		// Test 3: Repeated section name
		// Test 4: Repeated section links
		TreeSet<String> sectionNames = new TreeSet<>();
		TreeSet<String> sectionLinks = new TreeSet<>();
		for (Section section : site.getSections()) {
			if (!sectionNames.add(section.name)) {
				System.out.println("NaRep: " + section.name + " [" + section.url + "]");
			}
		}
		for (Section section : site.getSections()) {
			if (section.url != null) {
				if (!sectionLinks.add(section.url)) {
					repeatedUrlSectionsCounter++;
					System.out.println("LiRep: " + section.name + " [" + section.url + "]");
				}
			}
		}
		repeatedNameSectionsCounter = site.getSections().size() - sectionNames.size();

		System.out.println("Repeated section names: " + repeatedNameSectionsCounter);
		System.out.println("Repeated section links: " + repeatedUrlSectionsCounter);

		// Test 5: Repeated section content
		for (int i = 0; i < news.size(); i++) {
			NewsSet ni = news.get(i);
			for (int j = i + 1; j < news.size(); j++) {

				NewsSet nj = news.get(j);
				if (ni.size() != nj.size()) {
					continue;
				}

				int coincidences = 0;
				for (News nwi : ni) {
					for (News nwj : nj) {
						if (nwi.link.equals(nwj.link)) {
							coincidences++;
							break;
						}
					}
				}

				if (coincidences == ni.size()) {
					System.out.println("Coinciden: " + secNames.get(i) + " y " + secNames.get(j));
					repeatedSectionsContentCounter++;
					break;
				}
			}
		}
		System.out.println("Repeated section content: " + repeatedSectionsContentCounter);

	}
}
