package com.lucevent.newsup.parse;

public class NewsSubtitle implements NewsElement<String> {

	private String mContent;
	private boolean appendable = true;

	public NewsSubtitle()
	{
	}

	@Override
	public int getType()
	{
		return TYPE_SUBTITLE;
	}

	@Override
	public void setContent(String content)
	{
		this.mContent = content;
	}

	@Override
	public String getContent()
	{
		return mContent;
	}

	@Override
	public NewsElement getNewElement()
	{
		return new NewsSubtitle();
	}

	@Override
	public boolean isAppendable()
	{
		return appendable;
	}

	@Override
	public void setNotAppendable()
	{
		appendable = false;
	}

}
