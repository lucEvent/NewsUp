package com.lucevent.newsup.data.alert;

public interface AlertCode {

	String JSON_ID = "id";
	String JSON_PROBABILITY = "prob";

	String JSON_MESSAGE_CODE = "msg_code";
	String JSON_MESSAGE = "msg";

	String JSON_BTN_START_ACTION = "st_act";
	String JSON_BTN_START_CODE = "st_code";
	String JSON_BTN_START_TEXT = "st_text";

	String JSON_BTN_CENTER_ACTION = "ce_act";
	String JSON_BTN_CENTER_CODE = "ce_code";
	String JSON_BTN_CENTER_TEXT = "ce_text";

	String JSON_BTN_END_ACTION = "en_act";
	String JSON_BTN_END_CODE = "en_code";
	String JSON_BTN_END_TEXT = "en_text";

	int ACTION_DISMISS = -1;
	int ACTION_GOOGLE_PLAY = 0;
	int ACTION_REPORT = 1;
	int ACTION_DO_NOT_ASK_AGAIN = 2;
	int ACTION_OPEN_BOOKMARKS = 3;
	int ACTION_OPEN_EVENTS = 4;
	int ACTION_OPEN_HISTORY = 5;
	int ACTION_SEND_ANSWER = 6;
	int ACTION_OPEN_WEB_PAGE = 7;

	int MESSAGE_CUSTOM = -1;
	int MESSAGE_RATE_NOW = 0;
	int MESSAGE_NEEDS_UPDATE = 1;
	int MESSAGE_REPORT = 2;

	int BTN_HIDDEN = -2;
	int BTN_CUSTOM = -1;
	int BTN_YES = 0;
	int BTN_NO = 1;
	int BTN_CANCEL = 2;
	int BTN_UPDATE = 3;
	int BTN_NOT_NOW = 4;
	int BTN_RATE = 5;

}
