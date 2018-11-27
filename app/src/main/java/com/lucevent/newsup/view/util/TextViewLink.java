package com.lucevent.newsup.view.util;

import android.os.Parcel;
import android.text.style.URLSpan;
import android.view.View;

public class TextViewLink extends URLSpan {

	public TextViewLink(String url)
	{
		super(url);
	}

	public TextViewLink(Parcel src)
	{
		super(src);
	}

	@Override
	public void onClick(View widget)
	{
		Utils.openCustomTab(widget.getContext(), getURL());
	}

}