package com.lucevent.newsup.parse;

public class NewsListItem extends NewsElement<String> {

    private String mContent;

    public NewsListItem()
    {
        super(true);
    }

    @Override
    public int getType()
    {
        return TYPE_LIST_ITEM;
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
        return new NewsListItem();
    }

}
