package com.lucevent.newsup.services;

import android.app.Activity;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.data.alert.Alert;
import com.lucevent.newsup.data.alert.AlertCode;
import com.lucevent.newsup.view.util.AppAlertDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;
import java.util.Random;
import java.util.TreeSet;

public class AlertService implements AlertCode {

    public interface OnAlertListener {
        void onAlert(Alert alert);
    }

    public Activity mContext;
    public OnAlertListener mCallBack;

    public AlertService(Activity context, OnAlertListener callBack)
    {
        mContext = context;
        mCallBack = callBack;
    }


    public void fetch(final String appVersion)
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                try {
                    String url = "http://newsup-2406.appspot.com/appv2?alerts&lang=" + Locale.getDefault().getLanguage() + "&v=" + appVersion;

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(new URL(url).openStream()));

                    char[] buff = new char[1024];
                    StringBuilder sb = new StringBuilder(1000);
                    int len;
                    while ((len = in.read(buff, 0, buff.length)) > 0)
                        sb.append(buff, 0, len);

                    in.close();

                    //
                    JSONArray json = new JSONArray(sb.toString());
                    TreeSet<Alert> alerts = new TreeSet<>();

                    for (int i = 0; i < json.length(); i++) {
                        Alert alert = new Alert();
                        JSONObject jsonAlert = (JSONObject) json.get(i);

                        alert.id = jsonAlert.getInt(JSON_ID);
                        alert.probability = jsonAlert.getInt(JSON_PROBABILITY);

                        alert.message_code = jsonAlert.getInt(JSON_MESSAGE_CODE);
                        alert.message = jsonAlert.getString(JSON_MESSAGE);

                        alert.btn_start_action = jsonAlert.getInt(JSON_BTN_START_ACTION);
                        alert.btn_start_code = jsonAlert.getInt(JSON_BTN_START_CODE);
                        alert.btn_start_text = jsonAlert.getString(JSON_BTN_START_TEXT);

                        alert.btn_center_action = jsonAlert.getInt(JSON_BTN_CENTER_ACTION);
                        alert.btn_center_code = jsonAlert.getInt(JSON_BTN_CENTER_CODE);
                        alert.btn_center_text = jsonAlert.getString(JSON_BTN_CENTER_TEXT);

                        alert.btn_end_action = jsonAlert.getInt(JSON_BTN_END_ACTION);
                        alert.btn_end_code = jsonAlert.getInt(JSON_BTN_END_CODE);
                        alert.btn_end_text = jsonAlert.getString(JSON_BTN_END_TEXT);

                        alerts.add(alert);
                    }

                    Random rand = new Random();
                    for (final Alert alert : alerts) {
                        if (alert.probability >= rand.nextInt(100) && !AppSettings.wasAlertShown(alert.id)) {

                            mContext.runOnUiThread(new Runnable() {
                                @Override
                                public void run()
                                {
                                    new AppAlertDialog(mContext)
                                            .prepare(alert)
                                            .start();
                                }
                            });

                            return;
                        }
                    }

                } catch (Exception e) {
                    AppSettings.printerror("[AS] Error on Alert Service", e);
                }
            }
        }).start();
    }

}