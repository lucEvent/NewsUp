package com.lucevent.newsup.parse;

public class NewsTitle extends NewsElement<String> {

    private String mContent;

    public NewsTitle()
    {
        super(false);
    }

    @Override
    public int getType()
    {
        return TYPE_TITLE;
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
        return new NewsTitle();
    }

}
