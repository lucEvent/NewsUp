package com.lucevent.newsup.net;

import com.lucevent.newsup.AppSettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class RawContentReader {

	public static StringBuilder getUrl(String url) throws IOException
	{
		AppSettings.printlog(url);

		BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()));

		StringBuilder sb = new StringBuilder();
		int len = 2048;
		char[] buffer = new char[len];

		while ((len = in.read(buffer, 0, len)) > 0)
			sb.append(buffer, 0, len);

		in.close();

		return sb;
	}

}
