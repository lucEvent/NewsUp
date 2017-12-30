package com.lucevent.newsup.parse;

public class NewsSubtitle extends NewsElement<String> {

    private String mContent;

    public NewsSubtitle()
    {
        super(true);
    }

    @Override
    public int getType()
    {
        return TYPE_SUBTITLE;
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
        return new NewsSubtitle();
    }

}
