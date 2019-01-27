package com.lucevent.newsup.backend.utils;

import com.lucevent.newsup.backend.db.Poll;
import com.lucevent.newsup.data.alert.Alert;
import com.lucevent.newsup.data.alert.AlertCode;

public class Alerts extends java.util.ArrayList<Alert>
		implements com.lucevent.newsup.data.alert.AlertCode {

	private static final int PROB_NOT_IMPORTANT = 7;
	private static final int PROB_IMPORTANT = 60;
	private static final int PROB_VERY_IMPORTANT = 100;

	public Alerts()
	{
		super(6);
	}

	public void addUpdateAlert()
	{
		Alert updateAlert = new Alert();
		updateAlert.id = (int) System.currentTimeMillis();
		updateAlert.probability = PROB_VERY_IMPORTANT;
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
		updateAlert.probability = PROB_NOT_IMPORTANT;
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
		updateAlert.probability = PROB_NOT_IMPORTANT;
		updateAlert.message_code = MESSAGE_RATE_NOW;

		updateAlert.btn_start_code = BTN_NO;
		updateAlert.btn_start_action = ACTION_DO_NOT_ASK_AGAIN;

		updateAlert.btn_center_code = BTN_NOT_NOW;
		updateAlert.btn_center_action = ACTION_DISMISS;

		updateAlert.btn_end_code = BTN_RATE;
		updateAlert.btn_end_action = ACTION_GOOGLE_PLAY;
		add(updateAlert);
	}

	public void addPolls(String lang)
	{
	/*	Alert updateAlert = new Alert();
		updateAlert.id = 532452437;
		updateAlert.probability = PROB_VERY_IMPORTANT;
		updateAlert.message_code = MESSAGE_CUSTOM;
		updateAlert.message = "This is the poll test #2. (OL\u00C9) Just click on one of the options. ok?";

		updateAlert.btn_start_code = BTN_NO;
		updateAlert.btn_start_action = ACTION_SEND_ANSWER;

		updateAlert.btn_center_code = BTN_CUSTOM;
		updateAlert.btn_center_action = ACTION_SEND_ANSWER;
		updateAlert.btn_center_text = "C\u00FAstom:)";

		updateAlert.btn_end_code = BTN_YES;
		updateAlert.btn_end_action = ACTION_SEND_ANSWER;
		add(updateAlert);*/
	}

	public void addOldPolls()
	{
	}

	public void pollAnswer(long poll_id, int answer)
	{
		Poll poll = Poll.get(poll_id);

		if (poll == null) {
			addPolls("en");
			addOldPolls();

			Alert poll_alert = null;
			for (Alert a : this)
				if (a.id == poll_id) {
					poll_alert = a;
					break;
				}

			if (poll_alert == null)
				return;

			poll = new Poll();
			poll.init(
					poll_id,
					poll_alert.btn_start_code != AlertCode.BTN_CUSTOM ? poll_alert.btn_start_code : poll_alert.btn_start_text.hashCode(),
					poll_alert.btn_center_code != AlertCode.BTN_CUSTOM ? poll_alert.btn_center_code : poll_alert.btn_center_text.hashCode(),
					poll_alert.btn_end_code != AlertCode.BTN_CUSTOM ? poll_alert.btn_end_code : poll_alert.btn_end_text.hashCode()
			);
		}

		if (answer == poll.getAnswer1Code())
			poll.incAnswer1Counter();
		else if (answer == poll.getAnswer2Code())
			poll.incAnswer2Counter();
		else if (answer == poll.getAnswer3Code())
			poll.incAnswer3Counter();

		poll.save();
	}

}
