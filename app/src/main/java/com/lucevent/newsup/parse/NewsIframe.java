package com.lucevent.newsup.parse;

public class NewsIframe extends NewsElement<String> {

    private String src;

    public NewsIframe()
    {
        super(false);
    }

    @Override
    public int getType()
    {
        return TYPE_IFRAME;
    }

    @Override
    public void setContent(String content)
    {
        this.src = content;
    }

    @Override
    public String getContent()
    {
        return src;
    }

    @Override
    public NewsElement getNewElement()
    {
        return new NewsIframe();
    }

}
