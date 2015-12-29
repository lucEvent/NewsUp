package com.newsup.settings;

import java.util.ArrayList;

public class AppSettings {

    public static final int SET_MAIN_CODES = 0;
    public static final int ADD_FAV_CODE = 1;
    public static final int DEL_FAV_CODE = 2;
    public static final int ADD_DL_SCHEDULE = 3;
    public static final int DEL_DL_SCHEDULE = 4;
    public static final int MOD_DL_SCHEDULE = 5;

    public static int[] MAIN_CODES;
    public static ArrayList<Integer> FAV_CODES;
    public static ArrayList<DownloadScheduleSetting> DL_SCHEDULES;

    public AppSettings() {
        MAIN_CODES = new int[]{105};
        FAV_CODES = new ArrayList<Integer>();
        DL_SCHEDULES = new ArrayList<DownloadScheduleSetting>();
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
            case ADD_DL_SCHEDULE:
                DL_SCHEDULES.add((DownloadScheduleSetting) setting);
                break;
            case DEL_DL_SCHEDULE:
                int position = (Integer) setting;
                DL_SCHEDULES.remove(position);
                break;
        }
    }

    public void updateSetting(int settingcode, int position, Object setting) {
        switch (settingcode) {
            case MOD_DL_SCHEDULE:
                if (position < DL_SCHEDULES.size()) {
                    DL_SCHEDULES.remove(position);
                    DL_SCHEDULES.add(position, (DownloadScheduleSetting) setting);
                }
                break;
        }
    }

}