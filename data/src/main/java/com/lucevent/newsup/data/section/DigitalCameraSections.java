package com.lucevent.newsup.data.section;

import com.lucevent.newsup.data.util.Section;
import com.lucevent.newsup.data.util.Sections;

public class DigitalCameraSections extends Sections {

	public DigitalCameraSections()
	{
		super();

		add(new Section("Principal", "http://www.digitalcamera.es/feed/", 0));
		add(new Section("An\u00E1lisis", "http://www.digitalcamera.es/category/analisis/feed/", 0));
		add(new Section("Accesorios", "http://www.digitalcamera.es/category/analisis/accesorios/feed/", 1));

	}

}
