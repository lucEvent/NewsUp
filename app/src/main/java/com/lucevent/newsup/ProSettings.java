package com.lucevent.newsup;

import android.content.SharedPreferences;

public class ProSettings {

	private static boolean DEVELOPER_MODE;
	private static final int DEVELOPER_MODE_ON_CODE = -80680689;
	private static final int DEVELOPER_MODE_OFF_CODE = 1793865220;
	private static final String DEVELOPER_MODE_KEY = "devmode";

	private static final int FINLAND_SITES_CODE = -593347822;
	private static final String FINLAND_SITES_KEY = "finlandsites";

	private static SharedPreferences preferences;

	public static void initialize(SharedPreferences preferences)
	{
		ProSettings.preferences = preferences;
		ProSettings.DEVELOPER_MODE = preferences != null && preferences.getBoolean(DEVELOPER_MODE_KEY, false);
	}

	public static boolean isPremiumModeEnabled()
	{
		return false;
	}

	public static boolean isDeveloperModeEnabled()
	{
		return ProSettings.DEVELOPER_MODE;
	}

	public static boolean areFinnishPublicationsEnabled()
	{
		return DEVELOPER_MODE || (preferences != null && preferences.getBoolean(FINLAND_SITES_KEY, false));
	}

	public static int checkProCode(String code)
	{
		switch (hash(code)) {
			case DEVELOPER_MODE_ON_CODE:
				edit(DEVELOPER_MODE_KEY, true);
				DEVELOPER_MODE = true;
				return R.string.msg_developer_mode_on;
			case DEVELOPER_MODE_OFF_CODE:
				edit(DEVELOPER_MODE_KEY, false);
				DEVELOPER_MODE = false;
				return R.string.msg_developer_mode_off;
			case FINLAND_SITES_CODE:
				edit(FINLAND_SITES_KEY, true);
				return R.string.msg_unlocked_finnish;
			default:
				return R.string.msg_invalid_code;
		}
	}

	private static void edit(String key, boolean value)
	{
		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean(key, value);
		editor.apply();
	}

	private static int hash(String c)
	{
		int code = c.toLowerCase().hashCode();
		return code + (Integer.bitCount(code) << ((code >> 16) & 32));
	}

}
