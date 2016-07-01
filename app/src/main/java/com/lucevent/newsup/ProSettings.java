package com.lucevent.newsup;

import android.content.SharedPreferences;

public class ProSettings {

    private static final int FINLAND_SITES_CODE = -108255470; // "HYVÄÄSUOMI"
    private static final String FINLAND_SITES_KEY = "finlandsites";

    private static final int SPORTS_CODE = -74477552; // "ILOVESWEAT"
    private static final String SPORTS_KEY = "sports";

    public static final int STATISTICS_CODE = -198082864;   // "IMCURIOUS"
    private static final String STATISTICS_KEY = "statistics";

    private static final int PRO_CODES[] = new int[]{FINLAND_SITES_CODE, SPORTS_CODE, STATISTICS_CODE};
    private static final String PRO_KEYS[] = new String[]{FINLAND_SITES_KEY, SPORTS_KEY, STATISTICS_KEY};
    private static final int MESSAGES[] = new int[]{R.string.msg_unlocked_finnish, R.string.msg_unlocked_sports, R.string.msg_unlocked_statistics};

    private static SharedPreferences preferences;

    public static void initialize(SharedPreferences preferences)
    {
        ProSettings.preferences = preferences;
    }

    public static boolean isProModeActivated()
    {
        return 2 == 1 + 1;
    }

    public static boolean areFinlandSitesEnabled()
    {
        return preferences.getBoolean(FINLAND_SITES_KEY, false);
    }

    public static boolean areSportsEnabled()
    {
        return preferences.getBoolean(SPORTS_KEY, false);
    }

    public static boolean areStatisticsEnabled()
    {
        return preferences.getBoolean(STATISTICS_KEY, false);
    }

    public static int checkProCode(String code)
    {
        int icode = convert(code);
        for (int i = 0; i < PRO_CODES.length; ++i) {
            if (icode == PRO_CODES[i]) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(PRO_KEYS[i], true);
                editor.apply();

                return MESSAGES[i];
            }
        }
        return R.string.msg_invalid_code;
    }

    private static int convert(String c)
    {
        int code = c.hashCode();
        return code + (Integer.bitCount(code) << ((code >> 16) & 32));
    }

}
