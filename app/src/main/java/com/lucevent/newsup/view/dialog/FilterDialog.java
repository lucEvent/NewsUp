package com.lucevent.newsup.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucevent.newsup.R;
import com.lucevent.newsup.data.Sites;
import com.lucevent.newsup.data.util.News;
import com.lucevent.newsup.data.util.Site;
import com.lucevent.newsup.kernel.AppData;
import com.lucevent.newsup.view.util.FilterCheckBox;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class FilterDialog implements DialogInterface.OnClickListener {

	public interface Callback {
		void onFilter(TreeSet<Integer> f);
	}

	private Context mContext;
	private Callback mCallback;
	private Sites mSites;

	public FilterDialog(Context context)
	{
		mContext = context;
	}

	public FilterDialog news(ArrayList<News> news)
	{
		TreeSet<Integer> siteCodes = new TreeSet<>();
		for (int i = 0; i < news.size(); i++)
			siteCodes.add(news.get(i).site_code);

		mSites = new Sites();
		for (Integer c : siteCodes)
			mSites.add(AppData.getSiteByCode(c));

		return this;
	}

	public FilterDialog listener(Callback callback)
	{
		mCallback = callback;
		return this;
	}

	private ArrayList<FilterCheckBox> mLanguageCBs, mCountryCBs, mPublicationCBs;
	private AlertDialog mDialog;

	public void show()
	{
		if (mDialog == null)
			create();

		mDialog.show();
	}

	private void create()
	{
		Resources r = mContext.getResources();
		String[] titles_languages = r.getStringArray(R.array.site_languages);
		String[] titles_countries = r.getStringArray(R.array.site_countries);
		int[] language_weights = r.getIntArray(R.array.site_language_weights);
		int[] country_weights = r.getIntArray(R.array.site_country_weights);

		TreeMap<Integer, Site> languageCodes = new TreeMap<>();
		TreeMap<Integer, Site> countryCodes = new TreeMap<>();
		TreeMap<String, Site> names = new TreeMap<>();

		for (Site s : mSites) {
			languageCodes.put(language_weights[s.getLanguage() - 1], s);
			countryCodes.put(country_weights[s.getCountry() - 1], s);
			names.put(s.name, s);
		}

		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.d_filter, null);
		((FilterCheckBox) view.findViewById(R.id.cb_languages)).setOnCheckedChangeListener(onGroupFilterChangeState);
		((FilterCheckBox) view.findViewById(R.id.cb_countries)).setOnCheckedChangeListener(onGroupFilterChangeState);
		((FilterCheckBox) view.findViewById(R.id.cb_publications)).setOnCheckedChangeListener(onGroupFilterChangeState);

		ViewGroup vgLanguages = (ViewGroup) view.findViewById(R.id.languages);
		mLanguageCBs = new ArrayList<>();
		for (Site s : languageCodes.values()) {
			FilterCheckBox fcb = new FilterCheckBox(mContext, null);
			fcb.setText(titles_languages[s.getLanguage() - 1]);
			fcb.setTag(s.getLanguage());
			vgLanguages.addView(fcb);
			mLanguageCBs.add(fcb);
		}
		ViewGroup vgCountries = (ViewGroup) view.findViewById(R.id.countries);
		mCountryCBs = new ArrayList<>();
		for (Site s : countryCodes.values()) {
			FilterCheckBox fcb = new FilterCheckBox(mContext, null);
			fcb.setText(titles_countries[s.getCountry() - 1]);
			fcb.setTag(s.getCountry());
			vgCountries.addView(fcb);
			mCountryCBs.add(fcb);
		}
		ViewGroup vgSites = (ViewGroup) view.findViewById(R.id.publications);
		mPublicationCBs = new ArrayList<>();
		for (Site s : names.values()) {
			FilterCheckBox fcb = new FilterCheckBox(mContext, null);
			fcb.setText(s.name);
			fcb.setTag(s);
			vgSites.addView(fcb);
			mPublicationCBs.add(fcb);
		}

		mDialog = new AlertDialog.Builder(mContext)
				.setView(view)
				.setPositiveButton(R.string.done, this)
				.create();
	}

	@Override
	public void onClick(DialogInterface dialog, int which)
	{
		TreeSet<Integer> filter = new TreeSet<>();

		for (FilterCheckBox cb : mLanguageCBs)
			if (cb.isChecked()) {
				int lang = (int) cb.getTag();
				for (Site s : mSites)
					if (s.getLanguage() == lang)
						filter.add(s.code);
			}
		for (FilterCheckBox cb : mCountryCBs)
			if (cb.isChecked()) {
				int country = (int) cb.getTag();
				for (Site s : mSites)
					if (s.getCountry() == country)
						filter.add(s.code);
			}
		for (FilterCheckBox cb : mPublicationCBs)
			if (cb.isChecked())
				filter.add(((Site) cb.getTag()).code);

		if (!filter.isEmpty())
			mCallback.onFilter(filter);
	}

	private FilterCheckBox.OnCheckedChangeListener onGroupFilterChangeState = new FilterCheckBox.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(FilterCheckBox groupCB, boolean isChecked)
		{
			ArrayList<FilterCheckBox> sub;
			switch (groupCB.getId()) {
				case R.id.cb_languages:
					sub = mLanguageCBs;
					break;
				case R.id.cb_countries:
					sub = mCountryCBs;
					break;
				case R.id.cb_publications:
					sub = mPublicationCBs;
					break;
				default:
					return;
			}
			for (FilterCheckBox cb : sub)
				cb.setChecked(isChecked);
		}
	};

}
