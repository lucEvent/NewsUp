package com.lucevent.newsup.data.util;

public class Site {

	public final int code;

	public final String name, url;

	public final int color;
	private final double colorDarkness;

	public final int info;

	private int num_readings;

	public NewsMap news;

	private Class readerClass;
	protected Reader reader;

	private Class sectionsClass;
	protected Sections sections;

	public Site(int code, String name, int color, String url, int info, Class sectionsClass, Class readerClass)
	{
		this.code = code;
		this.name = name;
		this.url = url;
		this.info = 0x1000000 | info;
		this.num_readings = 0;
		this.color = color;
		this.sectionsClass = sectionsClass;
		this.readerClass = readerClass;

		colorDarkness = 1 - (0.299 * ((color >> 16) & 0xFF) + 0.587 * ((color >> 8) & 0xFF) + 0.114 * (color & 0xFF)) / 255;
	}

	public int getCountry()
	{
		return ((info >> SiteCountry.shift) & 0xFF);
	}

	public int getLanguage()
	{
		return ((info >> SiteLanguage.shift) & 0xFF);
	}

	public int getCategory()
	{
		return ((info >> SiteCategory.shift) & 0xFF);
	}

	public int getNumReadings()
	{
		return num_readings;
	}

	public void setNumReadings(int n)
	{
		num_readings = n;
	}

	public Sections getSections()
	{
		if (sections == null)
			sections = (Sections) load(sectionsClass);
		return sections;
	}

	public Reader getReader()
	{
		if (reader == null)
			reader = (Reader) load(readerClass);
		return reader;
	}

	public NewsArray readNewsHeaders(int[] section_codes)
	{
		NewsArray res = new NewsArray();

		Sections sections = getSections();
		for (int section_code : section_codes) {
			Section section = sections.getSectionByCode(section_code);
			if (section == null || section.url == null)
				continue;

			res.addAll(getReader().readRssHeader(section.url, code, section.code));
		}

		return res;
	}

	public void readNewsContent(News news)
	{
		getReader().readContent(news);
	}

	public double getColorDarkness()
	{
		return colorDarkness;
	}

	public boolean needsBrightColors()
	{
		return colorDarkness >= 0.3;
	}

	public String getStyle()
	{
		return getReader().style;
	}

	private Object load(Class _class)
	{
		try {
			return _class.getConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}