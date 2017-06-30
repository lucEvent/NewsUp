package com.lucevent.newsup;

import android.content.SharedPreferences;

public class ProSettings {

    private static final int FINLAND_SITES_CODE = -593347822;
    public static final String FINLAND_SITES_KEY = "finlandsites";

    private static final int STATISTICS_CODE = 2113281681;
    public static final String STATISTICS_KEY = "statistics";

    private static final int NOTES_CODE = 1793461659;
    public static final String NOTES_KEY = "notes";

    private static final int PRO_CODES[] = new int[]{FINLAND_SITES_CODE, STATISTICS_CODE, NOTES_CODE};
    private static final String PRO_KEYS[] = new String[]{FINLAND_SITES_KEY, STATISTICS_KEY, NOTES_KEY};
    private static final int MESSAGES[] = new int[]{R.string.msg_unlocked_finnish, R.string.msg_unlocked_statistics, R.string.msg_unlocked_notes};

    private static SharedPreferences preferences;

    public static void initialize(SharedPreferences preferences)
    {
        ProSettings.preferences = preferences;
    }

    public static boolean isProModeActivated()
    {
        return 2 == 1 + 1;
    }

    public static boolean checkEnabled(String key)
    {
        return preferences != null && (AppSettings.DEBUG || preferences.getBoolean(key, false));
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
        int code = c.toLowerCase().hashCode();
        return code + (Integer.bitCount(code) << ((code >> 16) & 32));
    }

}
