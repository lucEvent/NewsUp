package com.lucevent.newsup;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.net.MainChangeListener;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class AppSettings {

    public static final boolean DEBUG = true;

    /**
     * ************** Default values *********************
     **/
    private static Set<String> DEFAULT_MAIN_SECTIONS;
    private static Set<String> DEFAULT_DOWNLOAD_SECTIONS;
    private static final String DEFAULT_KEEP_TIME = "2592000";

    public static String PREF_MAIN_SITES_KEY;
    public static String PREF_FAVORITE_SITES_KEY;
    public static String PREF_SCHEDULE_DOWNLOADS_KEY;
    public static String PREF_CLEAN_CACHE_KEY;
    public static String PREF_PRO_CODE_KEY;
    public static String PREF_KEEP_NEWS_KEY;

    public static String PREF_SITE_MAIN_SECTIONS_KEY;
    public static String PREF_SITE_DOWNLOAD_SECTIONS_KEY;

    private static SharedPreferences preferences;

    public static void initialize(Context context)
    {
        if (preferences == null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
            PREF_MAIN_SITES_KEY = context.getString(R.string.pref_main_sites_key);
            PREF_FAVORITE_SITES_KEY = context.getString(R.string.pref_favorite_sites_key);
            PREF_SCHEDULE_DOWNLOADS_KEY = context.getString(R.string.pref_schedule_downloads_key);
            PREF_CLEAN_CACHE_KEY = context.getString(R.string.pref_clean_cache_key);
            PREF_PRO_CODE_KEY = context.getString(R.string.pref_pro_code_key);
            PREF_KEEP_NEWS_KEY = context.getString(R.string.pref_keep_news_key);

            PREF_SITE_MAIN_SECTIONS_KEY = context.getString(R.string.pref_main_sections_key);
            PREF_SITE_DOWNLOAD_SECTIONS_KEY = context.getString(R.string.pref_download_sections_key);

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
        return preferences.getStringSet(PREF_SITE_MAIN_SECTIONS_KEY + site.code, DEFAULT_MAIN_SECTIONS);
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

    public static void toggleFavorite(Site site)
    {
        String code = Integer.toString(site.code);

        Set<String> pref = new HashSet<>(getFavoriteSitesCodesString());

        if (!pref.remove(code))
            pref.add(code);

        setFavoriteSitesCodes(pref);

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
