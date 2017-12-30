package com.lucevent.newsup.parse;

public class NewsParagraph extends NewsElement<String> {

    private String mContent;

    public NewsParagraph()
    {
        super(true);
    }

    @Override
    public int getType()
    {
        return TYPE_PARAGRAPH;
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
        return new NewsParagraph();
    }

}
