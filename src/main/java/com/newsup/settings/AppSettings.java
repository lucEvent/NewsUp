package com.newsup.settings;

public class AppSettings {

    public static final int SET_MAIN_SITE = 0;

    public static int[] main_sites_i;

    public AppSettings() {
        main_sites_i = new int[]{3};
    }

    public void setSetting(int settingcode, Object setting) {
        switch (settingcode) {
            case SET_MAIN_SITE:
                main_sites_i = (int[]) setting;
                break;
        }
    }

    public boolean[] sitesOnLoadBooleanArray(int size) {
        boolean[] res = new boolean[size];
        for (int i = 0; i < size; ++i) res[i] = false;
        for (int i = 0; i < main_sites_i.length; ++i) res[main_sites_i[i]] = true;
        return res;
    }

}
