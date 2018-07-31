package com.lucevent.newsup.data.reader;

import com.lucevent.newsup.data.util.Enclosure;
import com.lucevent.newsup.data.util.NewsReader;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.parser.XmlTreeBuilder;

public class UserSiteReader extends NewsReader {

	public UserSiteReader()
	{
		super(TAG_ITEM_ITEMS_ENTRY,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK, TAG_ID},
				new int[]{TAG_DESCRIPTION, TAG_SUMMARY},
				new int[]{},
				new int[]{TAG_PUBDATE, TAG_DC_DATE, TAG_UPDATED, TAG_PUBLISHED},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE, TAG_MEDIA_CONTENT, TAG_IMAGE},
				"");
	}

	@Override
	protected String parseDescription(Element prop)
	{
		String description = jsoupParse(prop).text();
		return description.length() <= 300 ?
				description :
				description.substring(0, 300);
	}

	@Override
	protected Enclosure parseEnclosure(Element prop)
	{
		return prop.hasAttr("url") ? super.parseEnclosure(prop) : null;
	}

	@Override
	protected Document getDocument(String url)
	{
		try {
			return org.jsoup.Jsoup.connect(url)
					.timeout(10000)
					.userAgent(USER_AGENT)
					.parser(new Parser(new XmlTreeBuilder()))
					.validateTLSCertificates(false)
					.get();
		} catch (Exception ignored) {
		}
		return super.getDocument(url);
	}

}
