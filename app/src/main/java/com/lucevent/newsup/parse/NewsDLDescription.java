package com.lucevent.newsup.parse;

public class NewsDLDescription extends NewsElement<String> {

    private String mContent;

    public NewsDLDescription()
    {
        super(true);
    }

    @Override
    public int getType()
    {
        return TYPE_DL_DESCRIPTION;
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
        return new NewsDLDescription();
    }

}
