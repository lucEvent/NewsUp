package com.lucevent.newsup.parse;

public interface NewsElement<T> {

	int TYPE_TITLE = 0;
	int TYPE_SUBTITLE = 1;
	int TYPE_PARAGRAPH = 2;
	int TYPE_IMAGE = 3;
	int TYPE_CAPTION = 4;
	int TYPE_BLOCKQUOTE = 5;
	int TYPE_LIST_ITEM = 6;
	int TYPE_TWEET = 7;
	int TYPE_INSTAGRAM = 8;
	int TYPE_IFRAME = 9;
	int TYPE_DL_TITLE = 10;
	int TYPE_DL_DESCRIPTION = 11;
	int TYPE_TABLE = 12;
	int TYPE_VIDEO = 13;
	int TYPE_AUDIO = 14;
	int TYPE_WIDGET = 15;

	int getType();

	void setContent(String content);

	T getContent();

	NewsElement getNewElement();

	boolean isAppendable();

	void setNotAppendable();

}
