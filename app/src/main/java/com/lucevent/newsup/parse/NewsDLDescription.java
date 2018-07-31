package com.lucevent.newsup.parse;

public class NewsDLDescription implements NewsElement<String> {

	private String mContent;
	private boolean appendable = true;

	public NewsDLDescription()
	{
	}

	@Override
	public int getType()
	{
		return TYPE_DL_DESCRIPTION;
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
		return new NewsDLDescription();
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
