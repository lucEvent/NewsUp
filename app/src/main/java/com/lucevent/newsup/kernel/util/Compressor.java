package com.lucevent.newsup.kernel.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Compressor {

	public static void compress(String data, FileOutputStream stream) throws Exception
	{
		GZIPOutputStream gzip = new GZIPOutputStream(stream);
		gzip.write(data.getBytes("UTF-8"));
		gzip.flush();
		gzip.close();
	}

	public static String decompress(FileInputStream stream) throws Exception
	{
		byte[] buff = new byte[stream.available()];
		stream.read(buff, 0, buff.length);
		stream.close();

		GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(buff));
		BufferedReader bf = new BufferedReader(new InputStreamReader(gzip, "UTF-8"));

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = bf.readLine()) != null)
			sb.append(line);

		return sb.toString();
	}

}
