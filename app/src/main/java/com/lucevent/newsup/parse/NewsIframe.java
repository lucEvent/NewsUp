package com.lucevent.newsup.parse;

public class NewsIframe implements NewsElement<String> {

	private String src;

	public NewsIframe()
	{
	}

	@Override
	public int getType()
	{
		return TYPE_IFRAME;
	}

	@Override
	public void setContent(String content)
	{
		this.src = content;
	}

	@Override
	public String getContent()
	{
		return src;
	}

	@Override
	public NewsElement getNewElement()
	{
		return new NewsIframe();
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
