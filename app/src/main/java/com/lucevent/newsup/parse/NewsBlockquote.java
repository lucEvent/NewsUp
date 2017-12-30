package com.lucevent.newsup.parse;

public class NewsBlockquote extends NewsElement<String> {

    private String mContent;

    public NewsBlockquote()
    {
        super(true);
    }

    @Override
    public int getType()
    {
        return TYPE_BLOCKQUOTE;
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
        return new NewsBlockquote();
    }

}
