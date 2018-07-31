package com.lucevent.newsup.parse;

import java.util.ArrayList;

public class NewsBlockquote extends ArrayList<NewsElement> implements NewsElement<Object> {

	public NewsBlockquote()
	{
	}

	@Override
	public int getType()
	{
		return TYPE_BLOCKQUOTE;
	}

	@Override
	public void setContent(String content)
	{
	}

	@Override
	public Object getContent()
	{
		return null;
	}

	@Override
	public NewsElement getNewElement()
	{
		return new NewsBlockquote();
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
