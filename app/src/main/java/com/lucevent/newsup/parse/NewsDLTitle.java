package com.lucevent.newsup.parse;

public class NewsDLTitle implements NewsElement<String> {

	private String mContent;
	private boolean appendable = true;

	public NewsDLTitle()
	{
	}

	@Override
	public int getType()
	{
		return TYPE_DL_TITLE;
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
		return new NewsDLTitle();
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
