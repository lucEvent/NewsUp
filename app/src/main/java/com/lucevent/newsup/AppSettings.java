package com.lucevent.newsup;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.net.MainChangeListener;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class AppSettings {

    public static final boolean DEBUG = false;

    /**
     * ************** Default values *********************
     **/
    private static Set<String> DEFAULT_MAIN_SECTIONS;
    private static Set<String> DEFAULT_DOWNLOAD_SECTIONS;
    private static final String DEFAULT_KEEP_TIME = "2592000";
    private static final boolean DEFAULT_LOAD_IMAGES = true;
    private static final boolean DEFAULT_LOAD_IMAGES_ONLY_ON_WIFI = true;
    private static final boolean DEFAULT_COMPACTED_IMAGES = false;
    private static final boolean DEFAULT_NIGHT_MODE = false;

    public static String PREF_MAIN_SITES_KEY, PREF_FAVORITE_SITES_KEY, PREF_SCHEDULE_DOWNLOADS_KEY,
            PREF_CLEAN_CACHE_KEY, PREF_PRO_CODE_KEY, PREF_KEEP_NEWS_KEY, PREF_LOAD_IMAGES_KEY,
            PREF_COMPACTED_IMAGES_KEY, PREF_LOAD_IMAGES_ONLY_ON_WIFI_KEY;
    private static final String PREF_NIGHT_MODE_KEY = "night_mode";
    private static final String PREF_FONT_SIZE_KEY = "font_size";
    public static final String LAST_DATA_REVISION_KEY = "last_data_rev";

    public static String PREF_SITE_MAIN_SECTIONS_KEY;
    public static String PREF_SITE_DOWNLOAD_SECTIONS_KEY;

    private static SharedPreferences preferences;

    public static void initialize(Context context)
    {
        if (preferences == null) {
            Resources r = context.getResources();
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
            PREF_MAIN_SITES_KEY = r.getString(R.string.pref_main_sites_key);
            PREF_FAVORITE_SITES_KEY = r.getString(R.string.pref_favorite_sites_key);
            PREF_SCHEDULE_DOWNLOADS_KEY = r.getString(R.string.pref_schedule_downloads_key);
            PREF_CLEAN_CACHE_KEY = r.getString(R.string.pref_clean_cache_key);
            PREF_PRO_CODE_KEY = r.getString(R.string.pref_pro_code_key);
            PREF_KEEP_NEWS_KEY = r.getString(R.string.pref_keep_news_key);
            PREF_LOAD_IMAGES_KEY = r.getString(R.string.pref_load_images_key);
            PREF_COMPACTED_IMAGES_KEY = r.getString(R.string.pref_load_images_compact_key);
            PREF_LOAD_IMAGES_ONLY_ON_WIFI_KEY = r.getString(R.string.pref_load_images_wifi_only_key);

            PREF_SITE_MAIN_SECTIONS_KEY = r.getString(R.string.pref_main_sections_key);
            PREF_SITE_DOWNLOAD_SECTIONS_KEY = r.getString(R.string.pref_download_sections_key);

            DEFAULT_MAIN_SECTIONS = new TreeSet<>();
            DEFAULT_MAIN_SECTIONS.add("0");

            DEFAULT_DOWNLOAD_SECTIONS = new TreeSet<>();
            DEFAULT_DOWNLOAD_SECTIONS.add("0");
        }
        ProSettings.initialize(preferences);
    }

    private static MainChangeListener changeListener;

    public static void initialize(Context context, MainChangeListener changeListener)
    {
        initialize(context);
        AppSettings.changeListener = changeListener;
    }

    public static boolean firstStart()
    {
        return preferences.getStringSet(PREF_MAIN_SITES_KEY, null) == null;
    }

    public static int getIntValue(String key, int default_value)
    {
        return preferences.getInt(key, default_value);
    }

    public static void setValue(String key, int default_value)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, default_value);
        editor.apply();
    }

    public static Set<String> getMainSitesCodesString()
    {
        return preferences.getStringSet(PREF_MAIN_SITES_KEY, new TreeSet<String>());
    }

    public static int[] getMainSitesCodes()
    {
        return getIntArray(getMainSitesCodesString());
    }

    public static void setMainSitesCodes(Set<String> codes)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(PREF_MAIN_SITES_KEY, codes);
        editor.apply();
    }

    public static Set<String> getMainSectionsString(Site site)
    {
        return new HashSet<>(preferences.getStringSet(PREF_SITE_MAIN_SECTIONS_KEY + site.code, DEFAULT_MAIN_SECTIONS));
    }


    public static int[] getMainSections(Site site)
    {
        return getIntArray(getMainSectionsString(site));
    }

    public static void setMainSections(Site site, Set<String> set)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(PREF_SITE_MAIN_SECTIONS_KEY + site.code, set);
        editor.apply();
    }

    public static Set<String> getDownloadSectionsString(Site site)
    {
        return preferences.getStringSet(PREF_SITE_DOWNLOAD_SECTIONS_KEY + site.code, DEFAULT_DOWNLOAD_SECTIONS);
    }

    public static int[] getDownloadSections(Site site)
    {
        return getIntArray(getDownloadSectionsString(site));
    }

    public static void setDownloadSections(Site site, Set<String> set)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(PREF_SITE_DOWNLOAD_SECTIONS_KEY + site.code, set);
        editor.apply();
    }

    public static boolean isShownInMain(Site site)
    {
        String code = Integer.toString(site.code);
        return getMainSitesCodesString().contains(code);
    }

    public static void setFavoriteSitesCodes(Set<String> codes)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(PREF_FAVORITE_SITES_KEY, codes);
        editor.apply();
    }

    private static Set<String> getFavoriteSitesCodesString()
    {
        return preferences.getStringSet(PREF_FAVORITE_SITES_KEY, new TreeSet<String>());
    }

    public static int[] getFavoriteSitesCodes()
    {
        return getIntArray(getFavoriteSitesCodesString());
    }

    public static boolean isFavorite(Site site)
    {
        String code = Integer.toString(site.code);
        return getFavoriteSitesCodesString().contains(code);
    }

    public static void toggleFavorite(Site site, boolean notify)
    {
        String code = Integer.toString(site.code);

        Set<String> pref = new HashSet<>(getFavoriteSitesCodesString());

        if (!pref.remove(code))
            pref.add(code);

        setFavoriteSitesCodes(pref);

        if (notify)
            changeListener.onFavoritesChange();
    }

    public static void toggleShownInMain(Site site)
    {
        String code = Integer.toString(site.code);

        Set<String> pref = new HashSet<>(getMainSitesCodesString());

        if (!pref.remove(code))
            pref.add(code);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(PREF_MAIN_SITES_KEY, pref);
        editor.apply();

        changeListener.onMainistsChange();
    }

    public static boolean loadImages()
    {
        return preferences.getBoolean(PREF_LOAD_IMAGES_KEY, DEFAULT_LOAD_IMAGES);
    }

    public static boolean loadCompactedImages()
    {
        return preferences.getBoolean(PREF_COMPACTED_IMAGES_KEY, DEFAULT_COMPACTED_IMAGES);
    }

    public static boolean loadImagesOnlyOnWifi()
    {
        return preferences.getBoolean(PREF_LOAD_IMAGES_ONLY_ON_WIFI_KEY, DEFAULT_LOAD_IMAGES_ONLY_ON_WIFI);
    }

    private static int[] getIntArray(Set<String> set)
    {
        int index = 0;
        int[] res = new int[set.size()];
        for (String s : set)
            res[index++] = Integer.parseInt(s);
        return res;
    }

    public static void setCleanCache()
    {
        int cleaned = preferences.getInt(PREF_CLEAN_CACHE_KEY, 0);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(PREF_CLEAN_CACHE_KEY, cleaned + 1);
        editor.apply();
    }

    public static long getKeepTime()
    {
        return Long.parseLong(preferences.getString(PREF_KEEP_NEWS_KEY, DEFAULT_KEEP_TIME));
    }

    public static boolean getNightModeStatus()
    {
        return preferences.getBoolean(PREF_NIGHT_MODE_KEY, DEFAULT_NIGHT_MODE);
    }

    public static int getFontSize(int default_size)
    {
        return preferences.getInt(PREF_FONT_SIZE_KEY, default_size);
    }

    public static void setNightModeStatus(boolean status)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PREF_NIGHT_MODE_KEY, status);
        editor.apply();
    }

    public static void setFontSize(int font_size)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(PREF_FONT_SIZE_KEY, font_size);
        editor.apply();
    }

    private static final String PREF_ALERT_KEY = "alert_";

    public static boolean wasAlertShown(int id)
    {
        return preferences.getBoolean(PREF_ALERT_KEY + id, false);
    }

    public static void setAlertAsShown(int id)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PREF_ALERT_KEY + id, true);
        editor.apply();
    }

    private static final String PREF_EVENT_CONF_KEY = "evf_";

    public static TreeSet<Integer> getEventFilter(int event_code)
    {
        String filter = preferences.getString(PREF_EVENT_CONF_KEY + event_code, null);
        if (filter != null) {
            String[] codes = filter.substring(1, filter.length() - 1).split(", ");
            TreeSet<Integer> filterCodes = new TreeSet<>();
            for (String c : codes)
                filterCodes.add(Integer.parseInt(c));

            return filterCodes;
        }
        return null;
    }

    public static void setEventFilter(int event_code, TreeSet<Integer> filter)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_EVENT_CONF_KEY + event_code, filter == null ? null : Arrays.toString(filter.toArray()));
        editor.apply();
    }

    public static void printerror(String msg, Exception e)
    {
        if (DEBUG) {
            System.err.println(msg);

            if (e != null)
                e.printStackTrace();
        }
    }

    public static void printlog(String msg)
    {
        if (DEBUG)
            System.out.println(msg);
    }

}
