package com.lucevent.newsup.kernel;

import android.Manifest;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.event.Event;
import com.lucevent.newsup.data.event.Events;
import com.lucevent.newsup.data.util.Sections;
import com.lucevent.newsup.data.util.Site;

import java.util.Set;

public class AppData {

    private static final int DATA_REVISION_N = 4;

    public static final String[] STORAGE_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    private static Sites sites;

    private static Events events;

    public static void setSites(Sites sites)
    {
        AppData.sites = sites;

        int last_revision_n = AppSettings.getIntValue(AppSettings.LAST_DATA_REVISION_KEY, 0);
        if (last_revision_n < DATA_REVISION_N) {
            AppSettings.setValue(AppSettings.LAST_DATA_REVISION_KEY, DATA_REVISION_N);

            for (Site s : sites) {
                Set<String> newMainSections = correctSections(s, AppSettings.getMainSectionsString(s));
                if (newMainSections != null)
                    AppSettings.setMainSections(s, newMainSections);

                Set<String> newDownloadSections = correctSections(s, AppSettings.getDownloadSectionsString(s));
                if (newDownloadSections != null)
                    AppSettings.setDownloadSections(s, newDownloadSections);
            }
        }
    }

    public static Sites getSites()
    {
        return sites;
    }

    public static Site getSiteByCode(int code)
    {
        return sites.getSiteByCode(code);
    }

    public static Event getEvent(int code)
    {
        for (Event e : events)
            if (e.code == code)
                return e;

        return null;
    }

    public static Sites getSites(int[] codes)
    {
        Sites res = new Sites();
        for (int code : codes)
            res.add(sites.getSiteByCode(code));

        return res;
    }

    public static void setEvents(Events events)
    {
        AppData.events = events;
    }

    private static Set<String> correctSections(Site site, Set<String> section_indexes)
    {
        boolean corrected = false;
        String[] indexes_array = section_indexes.toArray(new String[section_indexes.size()]);
        Sections sections = site.getSections();

        for (int i = 0; i < indexes_array.length; i++) {
            int index = Integer.parseInt(indexes_array[i]);
            if (index > sections.size() - 1 || sections.get(index).url == null) {
                corrected = true;
                section_indexes.remove(indexes_array[i]);
            }
        }

        if (corrected) {
            if (section_indexes.isEmpty())
                section_indexes.add("0");

            return section_indexes;
        }
        return null;
    }

}
