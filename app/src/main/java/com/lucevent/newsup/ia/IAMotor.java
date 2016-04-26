package com.lucevent.newsup.ia;

import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.kernel.NewsManager;

import java.util.Comparator;
import java.util.TreeSet;

public class IAMotor implements Comparator<IASite> {


    public interface IAValues {
        long SITE_MAX_TIME_SINCE_LAST_ACCESS = 7 * 24 * 60 * 60 * 1000;
    }

    private final NewsManager publicdata;
    private final IAStorage storage;

    private final TreeSet<IASite> ositeList;

    public IAMotor(NewsManager publicdata, IAStorage storage)
    {
        this.publicdata = publicdata;
        this.storage = storage;
        // IA set up
        ositeList = new TreeSet<IASite>(this);
        storage.readSitelist(AppData.sites, ositeList);
    }

    public void registerView(Site site)
    {
   /*     IASite iaSite = ((IASite) site);
        ositeList.remove(iaSite);
        iaSite.registerAccess();
        ositeList.add(iaSite);
 */
    }

    public void registerView(News news)
    {

    }

    @Override
    public int compare(IASite s1, IASite s2)
    {
        int res = Integer.compare(s1.accesses.size(), s2.accesses.size());
        return res != 0 ? res : Long.compare(s1.last_access, s2.last_access);
    }

}