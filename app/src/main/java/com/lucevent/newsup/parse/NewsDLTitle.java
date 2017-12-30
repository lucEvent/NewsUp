package com.lucevent.newsup.parse;

public class NewsDLTitle extends NewsElement<String> {

    private String mContent;

    public NewsDLTitle()
    {
        super(true);
    }

    @Override
    public int getType()
    {
        return TYPE_DL_TITLE;
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
        return new NewsDLTitle();
    }

}
