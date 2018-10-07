package com.lucevent.newsup.data.reader;

import org.jsoup.nodes.Element;

public abstract class AbstractEnglishHuffingtonPost extends com.lucevent.newsup.data.util.NewsReader {

	public AbstractEnglishHuffingtonPost(String itemTag, int[] titleTags, int[] linkTags,
	                                     int[] descriptionTags, int[] contentTags, int[] dateTags,
	                                     int[] categoryTags, int[] enclosureTags, String style)
	{
		super(itemTag, titleTags, linkTags, descriptionTags, contentTags, dateTags, categoryTags, enclosureTags, style);
	}

	protected void fixVDBPlayer(org.jsoup.nodes.Element article)
	{
		for (Element i : article.select(".vdb_player script")) {
			String[] src = i.attr("src").split("/");

			String pid = src[5];
			String vdb = src[7].substring(0, src[7].indexOf("."));
			String vid = src[6];
			String url = "https://delivery.vidible.tv/htmlembed/" + pid + "/" + vdb + ".html?" + vid;

			Element p = i.parent();
			cleanAttributes(p);
			p.html(insertIframe(url));
		}
	}

}