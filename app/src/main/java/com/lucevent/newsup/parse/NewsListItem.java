package com.lucevent.newsup.parse;

public class NewsListItem implements NewsElement<String> {

	private String mContent;
	private boolean appendable = true;

	public NewsListItem()
	{
	}

	@Override
	public int getType()
	{
		return TYPE_LIST_ITEM;
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
		return new NewsListItem();
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
