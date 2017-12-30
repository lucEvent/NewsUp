package com.lucevent.newsup.parse;

public class NewsTweet extends NewsElement<String> {

    private String mContent;

    public NewsTweet()
    {
        super(false);
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

}
