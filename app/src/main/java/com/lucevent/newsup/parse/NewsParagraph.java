package com.lucevent.newsup.parse;

public class NewsParagraph implements NewsElement<String> {

	private String mContent;
	private boolean appendable = true;

	public NewsParagraph()
	{
	}

	@Override
	public int getType()
	{
		return TYPE_PARAGRAPH;
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
		return new NewsParagraph();
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
