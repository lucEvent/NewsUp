package com.lucevent.newsup.backend.utils;

import com.lucevent.newsup.data.alert.Alert;

public class Alerts extends java.util.ArrayList<Alert>
        implements com.lucevent.newsup.data.alert.AlertCode {

    public Alerts()
    {
        super(6);
    }

    public void addUpdateAlert()
    {
        Alert updateAlert = new Alert();
        updateAlert.id = (int) System.currentTimeMillis();
        updateAlert.probability = 100;
        updateAlert.message_code = MESSAGE_NEEDS_UPDATE;

        updateAlert.btn_start_code = BTN_NOT_NOW;
        updateAlert.btn_start_action = ACTION_DISMISS;

        updateAlert.btn_center_code = BTN_HIDDEN;

        updateAlert.btn_end_code = BTN_UPDATE;
        updateAlert.btn_end_action = ACTION_GOOGLE_PLAY;
        add(updateAlert);
    }

    public void addReportAlert()
    {
        Alert updateAlert = new Alert();
        updateAlert.id = 1;
        updateAlert.probability = 5;
        updateAlert.message_code = MESSAGE_REPORT;

        updateAlert.btn_start_code = BTN_NO;
        updateAlert.btn_start_action = ACTION_DO_NOT_ASK_AGAIN;

        updateAlert.btn_center_code = BTN_NOT_NOW;
        updateAlert.btn_center_action = ACTION_DISMISS;

        updateAlert.btn_end_code = BTN_YES;
        updateAlert.btn_end_action = ACTION_REPORT;
        add(updateAlert);
    }

    public void addRateAlert()
    {
        Alert updateAlert = new Alert();
        updateAlert.id = 2;
        updateAlert.probability = 5;
        updateAlert.message_code = MESSAGE_RATE_NOW;

        updateAlert.btn_start_code = BTN_NO;
        updateAlert.btn_start_action = ACTION_DO_NOT_ASK_AGAIN;

        updateAlert.btn_center_code = BTN_NOT_NOW;
        updateAlert.btn_center_action = ACTION_DISMISS;

        updateAlert.btn_end_code = BTN_UPDATE;
        updateAlert.btn_end_action = ACTION_GOOGLE_PLAY;
        add(updateAlert);
    }

}
