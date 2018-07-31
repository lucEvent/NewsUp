package com.lucevent.newsup.parse;

public class NewsInstagram implements NewsElement<String> {

	private String mContent;

	public NewsInstagram()
	{
	}

	@Override
	public int getType()
	{
		return TYPE_INSTAGRAM;
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
		return new NewsInstagram();
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
