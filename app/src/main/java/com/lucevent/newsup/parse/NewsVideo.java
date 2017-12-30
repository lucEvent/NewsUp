package com.lucevent.newsup.parse;

public class NewsVideo extends NewsElement<String> {

    private String mContent;

    public NewsVideo()
    {
        super(false);
    }

    @Override
    public int getType()
    {
        return TYPE_VIDEO;
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
        return new NewsVideo();
    }

}
