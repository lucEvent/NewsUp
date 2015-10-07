package com.newsup.settings;

public class AppSettings {

    public static final int MAIN_SITE_I = 2;

    public static int main_site_i;

    public AppSettings() {
        main_site_i = 2;
    }

    public void setSetting(int settingcode, Object setting) {
        switch (settingcode) {
            case MAIN_SITE_I:
                main_site_i = (Integer) setting;
                break;
        }
    }

}
