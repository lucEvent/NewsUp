package com.newsup.settings;

import java.util.ArrayList;

public class AppSettings {

    public static final int SET_MAIN_SITES = 0;
    public static final int ADD_FAV_SITE = 1;
    public static final int DEL_FAV_SITE = 2;

    public static int[] main_sites_i;
    public static ArrayList<Integer> favorite_list;

    public AppSettings() {
        main_sites_i = new int[]{2};
        favorite_list = new ArrayList<Integer>();
    }

    public void setSetting(int settingcode, Object setting) {
        switch (settingcode) {
            case SET_MAIN_SITES:
                main_sites_i = (int[]) setting;
                break;
            case ADD_FAV_SITE:
                favorite_list.add((Integer) setting);
                break;
            case DEL_FAV_SITE:
                favorite_list.remove(setting);
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