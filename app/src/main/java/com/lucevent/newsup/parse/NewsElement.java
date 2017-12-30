package com.lucevent.newsup.parse;

public abstract class NewsElement<T> {

    public static final int TYPE_TITLE = 0;
    public static final int TYPE_SUBTITLE = 1;
    public static final int TYPE_PARAGRAPH = 2;
    public static final int TYPE_IMAGE = 3;
    public static final int TYPE_CAPTION = 4;
    public static final int TYPE_BLOCKQUOTE = 5;
    public static final int TYPE_LIST_ITEM = 6;
    public static final int TYPE_TWEET = 7;
    public static final int TYPE_INSTAGRAM = 8;
    public static final int TYPE_IFRAME = 9;
    public static final int TYPE_DL_TITLE = 10;
    public static final int TYPE_DL_DESCRIPTION = 11;
    public static final int TYPE_TABLE = 12;
    public static final int TYPE_VIDEO = 13;
    public static final int TYPE_AUDIO = 14;
    public static final int TYPE_WIDGET = 15;

    public boolean isAppendable;

    public NewsElement(boolean appendable)
    {
        this.isAppendable = appendable;
    }

    public abstract int getType();

    public abstract void setContent(String content);

    public abstract T getContent();

    public abstract NewsElement getNewElement();

}
