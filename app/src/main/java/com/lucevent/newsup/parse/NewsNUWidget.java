package com.lucevent.newsup.parse;


public class NewsNUWidget implements NewsElement<String> {

	private String mContent;

	public NewsNUWidget()
	{
	}

	@Override
	public int getType()
	{
		return TYPE_WIDGET;
	}

	@Override
	public void setContent(String content)
	{
		mContent = content;
	}

	@Override
	public String getContent()
	{
		return mContent;
	}

	@Override
	public NewsElement getNewElement()
	{
		return new NewsNUWidget();
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
