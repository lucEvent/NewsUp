package com.lucevent.newsup.net;

public interface BackendNames {

	String MAIN_SERVER = "http://newsup-2406.appspot.com/";

	String APP_SERVLET = "appv3";

	String DEVELOPER_SERVLET = "dev";

	String MAIN_APP_SERVER = MAIN_SERVER + APP_SERVLET;

	String DEVELOPER_APP_SERVER = MAIN_SERVER + DEVELOPER_SERVLET;

}
