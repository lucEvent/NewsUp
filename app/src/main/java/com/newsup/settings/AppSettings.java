package com.newsup.settings;

import java.util.ArrayList;

public class AppSettings {

    public static final int SET_MAIN_CODES = 0;
    public static final int ADD_FAV_CODE = 1;
    public static final int DEL_FAV_CODE = 2;

    public static int[] MAIN_CODES;
    public static ArrayList<Integer> FAV_CODES;

    public AppSettings() {
        MAIN_CODES = new int[]{105};
        FAV_CODES = new ArrayList<Integer>();
    }

    public void setSetting(int settingcode, Object setting) {
        switch (settingcode) {
            case SET_MAIN_CODES:
                MAIN_CODES = (int[]) setting;
                break;
            case ADD_FAV_CODE:
                FAV_CODES.add((Integer) setting);
                break;
            case DEL_FAV_CODE:
                FAV_CODES.remove((Integer) setting);
                break;
        }
    }

}