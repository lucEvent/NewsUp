package com.lucevent.newsup.development;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Site;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Setter {

	public static void main(String[] args)
	{
		try {
			createSiteFile("pub_name");

			//createHTMLSiteList();
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void createHTMLSiteList()
	{
		Sites sites = Sites.getDefault(true);
		for (Site s : sites) {
			int id = s.code;
			int co = s.getCountry();
			int la = s.getLanguage();
			int ty = s.getCategory();
			String n = s.name;
			System.out.println("<li class='site' id='" + id + "' co='" + co + "' la='" + la + "' ty='" + ty + "'><img src='img/" + id + ".png'><span>" + n + "</span></li>");
		}
	}

	private static void createSiteFile(String site_names) throws IOException
	{
		System.out.println("######## Starting.... ###########\n");

		String path = "development\\src\\main\\java\\com\\lucevent\\newsup\\development\\";

		String readerPath = path + "reader\\";
		String sectionPath = path + "section\\";

		String readerLayout = readFile(readerPath + "SiteExample.java");
		String sectionsLayout = readFile(sectionPath + "SectionsExample.java");

		String[] a_site_names = site_names.split(",");
		for (String site_name : a_site_names) {
			String formattedName = formatSiteName(site_name);

			System.out.print("//Creating files for " + site_name + "... ");
			writeFile(readerPath + formattedName + ".java", readerLayout.replace("SiteExample", formattedName));
			writeFile(sectionPath + formattedName + "Sections.java", sectionsLayout.replace("SectionsExample", formattedName + "Sections"));
			System.out.println("Done.");

		}
		for (String site_name : a_site_names) {
			String formattedName = formatSiteName(site_name);
			System.out.println("s = new Site(0, \"" + site_name + "\", 0, \"\", 0, " + formattedName + "Sections.class, " + formattedName + ".class);");
		}

		System.out.println("\n######## .......Done! ###########");
	}

	private static String formatSiteName(String site_name)
	{
		StringBuilder sb = new StringBuilder(site_name);
		for (int i = sb.length() - 1; i >= 0; i--) {
			char c = sb.charAt(i);
			if (c == ' ' || (!Character.isAlphabetic(c) && !Character.isAlphabetic(c))) {
				sb.deleteCharAt(i);
			}
		}
		return sb.toString();
	}

	public static String readFile(String file) throws IOException
	{
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(file)));

		byte[] buffer = new byte[in.available()];
		in.read(buffer, 0, buffer.length);
		in.close();

		return new String(buffer);
	}

	public static void writeFile(String file, String content) throws IOException
	{
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file)));

		byte[] buffer = content.getBytes();
		out.write(buffer, 0, buffer.length);
		out.flush();
		out.close();
	}

}
