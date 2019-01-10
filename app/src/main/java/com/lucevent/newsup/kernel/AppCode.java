package com.lucevent.newsup.kernel;

public interface AppCode {

	int REQUEST_PERMISSION_BOOKMARK = 200;
	int REQUEST_PERMISSION_SAVE_IMAGE = 201;

	int ERROR = -9;

	int NEWS_COLLECTION = 8;

	int NEWS_LOADED = 11;
	int NO_INTERNET = 12;
	int REPORT_SEND_OK = 13;
	int ALERT = 14;

	int REQUEST_ADD_CONTENT = 30;
	int REQUEST_SITE = 31;

	int STATISTICS = 50;
	int TAG_IMAGE = 0xff13a6ef;

	String SITE_CODE = "site_code";
	String SOURCES = "sources";
	String SCHEDULE_ID = "schedule_id";
	String TARGET = "target";
	String REQUEST_CODE = "r.code";
	String SELECTED = "selected";
	String EVENT_CODE = "e.code";
	String NOTIFICATION = "notif";
	String TIME = "time";

	String ACTION = "action";
	String RESTART = "restart";

}
