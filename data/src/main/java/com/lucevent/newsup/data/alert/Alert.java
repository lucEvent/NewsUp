package com.lucevent.newsup.data.alert;

public class Alert implements Comparable<Alert> {

	public int id;

	public int probability;

	// Message
	public int message_code;

	public String message;

	// Btn start
	public int btn_start_action, btn_start_code;

	public String btn_start_text;

	// Btn center
	public int btn_center_action, btn_center_code;

	public String btn_center_text;

	// Btn end
	public int btn_end_action, btn_end_code;

	public String btn_end_text;


	@Override
	public int compareTo(Alert o)
	{
		int c = Integer.compare(probability, o.probability);
		return c != 0 ? c : 1;
	}

}
