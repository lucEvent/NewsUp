package com.lucevent.newsup.parse;

public class NewsImage implements NewsElement<String> {

	private String url;

	public NewsImage()
	{
	}

	@Override
	public int getType()
	{
		return TYPE_IMAGE;
	}

	@Override
	public void setContent(String content)
	{
		this.url = content;
	}

	@Override
	public String getContent()
	{
		return url;
	}

	@Override
	public NewsElement getNewElement()
	{
		return new NewsImage();
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
