package com.lucevent.newsup.parse;

public class NewsInstagram extends NewsElement<String> {

    private String mContent;

    public NewsInstagram()
    {
        super(false);
    }

    @Override
    public int getType()
    {
        return TYPE_INSTAGRAM;
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
        return new NewsInstagram();
    }

}
