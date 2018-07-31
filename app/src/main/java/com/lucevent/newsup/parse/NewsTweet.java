package com.lucevent.newsup.parse;

public class NewsTweet implements NewsElement<String> {

	private String mContent;

	public NewsTweet()
	{
	}

	@Override
	public int getType()
	{
		return TYPE_TWEET;
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
		return new NewsTweet();
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
