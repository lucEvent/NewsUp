package com.lucevent.newsup.parse;

public class NewsTitle implements NewsElement<String> {

	private String mContent;

	public NewsTitle()
	{
	}

	@Override
	public int getType()
	{
		return TYPE_TITLE;
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
		return new NewsTitle();
	}

	@Override
	public boolean isAppendable()
	{
		return false;
	}

	@Override
	public void setNotAppendable()
	{
	}

}
