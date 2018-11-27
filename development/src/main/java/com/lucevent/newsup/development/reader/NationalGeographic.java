package com.lucevent.newsup.development.reader;

import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class NationalGeographic extends com.lucevent.newsup.development.utils.NewsReader {

	//tags:

	public NationalGeographic()
	{
		super(TAG_ITEM_ITEMS,
				new int[]{TAG_TITLE},
				new int[]{TAG_LINK},
				new int[]{TAG_DESCRIPTION},
				new int[]{TAG_CONTENT_ENCODED},
				new int[]{TAG_PUBDATE},
				new int[]{TAG_CATEGORY},
				new int[]{TAG_ENCLOSURE},
				"style      // todo or empty");
	}

	@Override
	protected Document getDocument(String url)
	{
		String s;
		try {
			 s = getUrl(url).toString();

		}catch (Exception e){
			System.out.println("Exception");
			e.printStackTrace();
			return null;
		}

		System.out.println(s.length());

		Document d= org.jsoup.Jsoup.parse(s);

		if(d==null)
			System.out.println("doc es null");

		return d;
	}

	public static StringBuilder getUrl(String url) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()));

		StringBuilder sb = new StringBuilder();
		int len = 2048;
		char[] buffer = new char[len];

		while ((len = in.read(buffer, 0, len)) > 0){
			sb.append(buffer, 0, len);
			System.out.println("read");
		}

		in.close();

		return sb;
	}

}
