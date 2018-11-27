package com.lucevent.newsup.net;

import com.lucevent.newsup.AppSettings;
import com.lucevent.newsup.data.alert.Alert;
import com.lucevent.newsup.data.alert.AlertCode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Locale;
import java.util.Random;
import java.util.TreeSet;

public class AlertReader implements AlertCode, BackendNames {

	private final String mAppVersion;

	public AlertReader(String appVersion)
	{
		mAppVersion = appVersion;
	}

	public Alert fetch()
	{
		try {
			StringBuilder sb = RawContentReader.getUrl(
					MAIN_APP_SERVER + "?alerts=" + Locale.getDefault().getLanguage() + "&v=" + mAppVersion
			);

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
				int ri = rand.nextInt(100);
				if (alert.probability >= ri
						&& !AppSettings.wasAlertShown(alert.id))
					return alert;
			}

		} catch (Exception e) {
			AppSettings.printerror("[AR] Error on Alert Service", e);
		}
		return null;
	}

}
