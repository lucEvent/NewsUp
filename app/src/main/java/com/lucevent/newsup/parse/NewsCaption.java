package com.lucevent.newsup.parse;

public class NewsCaption implements NewsElement<String> {

	private String mContent;

	public NewsCaption()
	{
	}

	@Override
	public int getType()
	{
		return TYPE_CAPTION;
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
		return new NewsCaption();
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
