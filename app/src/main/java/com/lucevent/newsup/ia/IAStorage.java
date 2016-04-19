package com.lucevent.newsup.ia;

import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.io.SDManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;

public class IAStorage {

    private static final String IA_DIR = "user";
    private static final String IA_IND = "index.nu";

    public IAStorage() {
        File dir = new File(SDManager.getDirectory(), IA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void readSitelist(Sites siteList, TreeSet<IASite> ositeList) {
/*        try {
            File dir = new File(SDManager.getDirectory(), IA_DIR);

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(dir, IA_IND)));

            int nSites = in.readInt();

            for (int i = 0; i < nSites; i++) {
                Site site = siteList.get(in.readInt());
                site.last_access = in.readLong();

                int nAccesses = in.readInt();
                long time = System.currentTimeMillis();
                for (int j = 0; j < nAccesses; j++) {
                    long accesstime = in.readLong();
                    if (accesstime + IAMotor.IAValues.SITE_MAX_TIME_SINCE_LAST_ACCESS < time) {
                        site.accesses.add(accesstime);
                    }
                }
                ositeList.add(site);
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/    }

    public void saveSitelist(TreeSet<IASite> list) {
        try {
            File dir = new File(SDManager.getDirectory(), IA_DIR);

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(dir, IA_IND)));

            out.writeInt(list.size());

            for (IASite site : list) {
                out.writeInt(site.code);
                out.writeLong(site.last_access);

                out.writeInt(site.accesses.size());
                for (int j = 0; j < site.accesses.size(); j++) {
                    out.writeLong(site.accesses.get(j));
                }
            }

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

