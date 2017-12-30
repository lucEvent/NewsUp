package com.lucevent.newsup.parse;

public class NewsImage extends NewsElement<String> {

    private String url;

    public NewsImage()
    {
        super(false);
    }

    @Override
    public int getType()
    {
        return TYPE_IMAGE;
    }

    @Override
    public void setContent(String content)
    {
        this.url = content;
    }

    @Override
    public String getContent()
    {
        return url;
    }

    @Override
    public NewsElement getNewElement()
    {
        return new NewsImage();
    }

}
