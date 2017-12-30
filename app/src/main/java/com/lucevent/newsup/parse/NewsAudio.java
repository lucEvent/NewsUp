package com.lucevent.newsup.parse;

public class NewsAudio extends NewsElement<String> {

    private String mContent;

    public NewsAudio()
    {
        super(false);
    }

    @Override
    public int getType()
    {
        return TYPE_AUDIO;
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
        return new NewsAudio();
    }

}
