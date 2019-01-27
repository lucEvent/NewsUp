package com.lucevent.newsup.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lucevent.newsup.R;
import com.lucevent.newsup.net.BackendNames;
import com.lucevent.newsup.view.activity.ContactActivity;
import com.lucevent.newsup.view.util.AppTextView;
import com.lucevent.newsup.view.util.Utils;

public class AboutFragment extends android.app.Fragment implements BackendNames {

	private static final String URL_TERM_AND_CONDITIONS = MAIN_SERVER + "term_and_conditions/";

	private static final String[] VERSION_CHANGELOG = {
			"2.9.0", "2019-XX-XX", "Find publication now offers better results", "Text is now selectable.", "News from not supported publications can now be bookmarked and will be keep in records.", "Section groups are now expandable/contractable in dialogs.", "Support for all IGN publications.", "Supported multi-regional publications adapt now to device language.", "App icon shortcut for 'Happening Now' section.", "Bug fixes and usability and performance improved."
	};

	private static final String[][] CHANGELOG = {
			{"2.8.1", "2018-10-10", "Support for IGN Spain", "Bug fixes from the previous version"},
			{"2.8.0", "2018-10-07", "Zoom images", "Headlines hands-free screen interaction improved", "Refresh news lists by swiping down", "About screen improved", "Now the current section is highlighted", "Support for Kinja publications", "Bug fixes and improvements"},
			{"2.7.1", "2018-08-03", "New: 'Hands-free' headlines view.", "New: Notifications (check drawer).", "New: Find external publications (RSS support needed).", "App night-mode shortcut in drawer.", "App icon shortcut to go to a specific publication.", "Download images by long-pressing on them.", "Clean Cache improved.", "Search ignoring accent marks in all search bars.", "Readers and Sections updated.", "Overall fixes and improvements."},
			{"2.6.0", "2018-02-17", "New: Night mode!", "Filters added in History.", "Filters and scheduled downloads for Events.", "Scheduled downloads improves.", "Compact view for news (check settings).", "Images load Wi-Fi only option (check settings).", "Font changes.", "RTL fixes.", "Some readers updated.", "Bug fixes."},
			{"2.5.0", "2017-12-30", "New NewsView improving the user experience.", "Search feature added on bookmarks and history screens.", "Support added for The Independent, Daily Mail, The Sun, Metro UK, Evening Standard, Daily Mirror, The National Scotland, Daily Express, Sports Illustrated and WIRED.", "Section dialog improved.", "Change font size option in NewsView.", "All sections revised.", "Twitter videos are now supported too.", "Small bug fixes."},
			{"2.4.0", "2017-10-05", "New: Events (Happening now).", "Added support for El Punt Avui, Catalonia Today, L'esportiu, Nació Digital, El Nacional, WWWhat's new?, Vogue España, CounterPunch, Lucky Puppy Mag and The Berry.", "Readers improves.", "Bug fixes."},
			{"2.3.2", "2017-08-20", "Publications list in navigation panel ordered by user's accesses.", "Database simplified.", "Visual bugs fixes on Kitkat.", "Various readers improved.", "Other miscellaneous fixes and improvements."},
			{"2.3.1", "2017-06-30", "Bug fixes (all bugs reported by users)."},
			{"2.3.0", "2017-06-22", "New: Select your favorite sections directly from the section popup list.", "Publication icons updated and resized throughout the app.", "Notification bug fixed.", "Usability improved, more bug fixes and optimizations."},
			{"2.2.0", "2017-03-14", "Loading of news list optimized.", "Night mode improved.", "Support for Astronomy Now, Chicago Suntimes, Coming soon, El Imparcial (Mx), El Periódico Extremadura, Full Músculo, Göteborgs-Posten, Omicrono, The Chive and The Conversation (UK, US, Australia, Africa and France versions)."},
			{"2.1.1", "2017-02-21", "Support for Pokemon Go news.", "New and faster download system."},
			{"2.1.0", "2017-02-10", "Added support for Andro4all, Topes de gama, La Patilla, Cosmo Noticias, Life Science Sweden, Teknikens Värld, Código Nuevo, The Geek Hammer and The Bolivar commercial. Sections and Readers updated. Instagram photos support."},
			{"2.0.0", "2016-11-09", "New: Shortcuts for each publication in home screen.", "New: Pictures on news list (and preference to show/hide them).", "New: More sections shown at the end of the news list (if available).", "New animations and some animation changes.", "News List view changed.", "Bookmark from News list.", "Some texts changed for better understanding.", "Schedule a download directly from one particular publication preference screen.", "Dark gray sections button for white color publications.", "Favorites added to preferences (finally).", "Main/Favorite/Schedule publications selection improved.", "Schedule downloads list now gives indications when there are no downloads scheduled.", "No more repeated news in History news list.", "Notifications improved :).", "New option buttons in news view (Plus night/day view option added).", "Sections button hidden when a publication has only one section.", "White icon color issue in Kitkat devices solved.", "In android 6 and above: Dark icons on notification bar with 'light-color' publications.", "Bug fixes and improvements."},
			{"1.8.0", "2016-10-10", "Added support for newspapers The Herald Scotland, The Guardian and The Times of India, and online magazines and blogs Clipset, Elle Spain, Google Earth Blog, TechCrunch, The Intercept and Vice."},
			{"1.7.2", "2016-10-02", "New: Report/Suggestion feature.", "Bookmarked news list updating fixed.", "Publication selection screen now orders by device locale, and each section by publication name.", "Full-translated to swedish.", "Partially translated to Finnish.", "Loading animation comes up when loading a section too now.", "More bug fixes and improvements."},
			{"1.7.0", "2016-09-24", "Added support for newspapers Abc, Metro UK, Siberian Times and USA Today, and online magazines and blogs Digital Inspiration, Meristation, PC World, The Verge and Vandal.", "Bug fixes."},
			{"1.6.0", "2016-09-12", "NewsView: images, videos and iframes are hidden when there is no internet connection.", "Fixed severe bug when a schedule is executed while the user is interacting with the app.", "Fixed severe bug opening news from notification.", "Support for tweets.", "News load faster.", "Added animation to show that news are loading.", "Introducing notes with Pro code.", "Avoid news reloading when unnecessary.", "Bug fixes and improvements."},
			{"1.5.1", "2016-07-08", "Screen overlapping on navigation back fixed, plus coherence with drawer row selected."},
			{"1.5.0", "2016-07-01", "Init screen to select your favorite publications.", "Drawer list reconfigured."},
			{"1.4.0", "2016-06-25", "Marshmallow support!!", "IMPORTANT FIX: Huge leak when closing news view fixed.", "News display style improved (margins).", "Zoom disabled."},
			{"1.3.0", "2016-06-16", "New: Pro codes.", "About screen performance improved.", "Database bug fixed."},
			{"1.2.3", "2016-06-02", "New 'Keep time' preference to control storage limits."},
			{"1.0.0", "2016-04-19", "Lollipop support!!", "Settings standardized.", "Minimum version supported: Kitkat"},
	};

	private static final String[] POSSIBLE_PUBLICATIONS = {
			"The Times",
			"Esquire",
			"Newslaundry",
			"Washington Post",
			"Dagen.se",
			"WATmag",
			"Thrive Global",
			"Revista Cuore",
			"DGiT",
			"Sound Guys",
			"Spaceflight Now",
			"NASA",
			"Svenska Yle",
			"Vagabond",
			"Science magazine",
			"The bark",
			"Nova dog magazine",
			"Ny Teknik",
			"Sweclockers",
			"Idg",
			"Veckans Affärer",
			"Tech Times",
			"Astrophysical journal",
			"BuzzFeed News",
			"BackChannel"
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container != null)
			container.removeAllViews();

		View v = inflater.inflate(R.layout.f_about, container, false);

		((TextView) v.findViewById(R.id.app_name_version))
				.setText(getString(R.string.app_name) + " " + getString(R.string.app_version));


		v.findViewById(R.id.about_new_in_this_version).setOnClickListener(onNewInThisVersionClickListener);
		v.findViewById(R.id.about_full_changelog).setOnClickListener(onFullChangeLogClickListener);
		v.findViewById(R.id.about_possible_additions).setOnClickListener(onPossibleAdditionsClickListener);
		v.findViewById(R.id.about_term_and_conditions).setOnClickListener(onTermAndConditionsClickListener);
		v.findViewById(R.id.about_contact).setOnClickListener(onContactClickListener);

		return v;
	}

	private View.OnClickListener onNewInThisVersionClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v)
		{
			StringBuilder sb = new StringBuilder();
			for (int i = 2; i < VERSION_CHANGELOG.length; i++)
				sb.append(" - ").append(VERSION_CHANGELOG[i]).append("<br>");

			showInfo(sb.toString());
		}
	};

	private View.OnClickListener onFullChangeLogClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v)
		{
			StringBuilder sb = new StringBuilder();
			for (String[] c : CHANGELOG) {
				sb.append("<b>version ").append(c[0]).append("</b> (").append(c[1]).append(")<br>");

				for (int i = 2; i < c.length; i++)
					sb.append(" - ").append(c[i]).append("<br>");

				sb.append("<br>");
			}

			showInfo(sb.toString());
		}
	};

	private View.OnClickListener onPossibleAdditionsClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v)
		{
			StringBuilder sb = new StringBuilder();
			for (String s : POSSIBLE_PUBLICATIONS)
				sb.append(" - ").append(s).append("<br>");

			showInfo(sb.toString());
		}
	};

	private View.OnClickListener onContactClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v)
		{
			startActivity(new Intent(getActivity(), ContactActivity.class));
		}
	};

	private View.OnClickListener onTermAndConditionsClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v)
		{
			Utils.openCustomTab(getActivity(), URL_TERM_AND_CONDITIONS);
		}
	};

	private void showInfo(String info)
	{
		Context c = getActivity();

		ScrollView v = new ScrollView(c);
		AppTextView tv = new AppTextView(getActivity());
		tv.setText(Html.fromHtml(info));
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
		tv.setPadding(padding, padding, padding, padding);
		tv.setLayoutParams(new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

		v.addView(tv);
		new AlertDialog.Builder(getActivity())
				.setView(v)
				.setPositiveButton(R.string.ok, null)
				.show();
	}

}
