package com.newsup.settings;

public class AppSettings {

    public static final int MAIN_SITE_I = 2;

    public static int[] main_sites_i;

    public AppSettings() {
        main_sites_i = new int[]{2};
    }

    public void setSetting(int settingcode, Object setting) {
        switch (settingcode) {
            case MAIN_SITE_I:
                main_sites_i = (int[]) setting;
                break;
        }
    }

}
